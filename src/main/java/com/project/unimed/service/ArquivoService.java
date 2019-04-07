package com.project.unimed.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.project.unimed.dao.MovieDAO;
import com.project.unimed.dao.ProducerDAO;
import com.project.unimed.dao.StudioDAO;
import com.project.unimed.dto.DTOQueryDuplicatedYear;
import com.project.unimed.dto.DTOQueryStudiosOnOrder;
import com.project.unimed.dto.DTOQueryWinnersProducers;
import com.project.unimed.model.Movie;
import com.project.unimed.model.Producer;
import com.project.unimed.model.Studio;

@Service
public class ArquivoService implements IArquivoService {

	@Autowired
	ProducerDAO producerDAO;

	@Autowired
	StudioDAO studioDAO;

	@Autowired
	MovieDAO movieDAO;

	boolean carregarArquivo = true;

	@Override
	public String carregar() {
		FileInputStream fis = null;
		CSVReader reader;
		String[] nextLine;

		try {

			String arqProducer = "src/main/resources/producer.csv";

			fis = new FileInputStream(new File(arqProducer));
			reader = new CSVReader(new InputStreamReader(fis));
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				Producer producer = new Producer();
				producer.setProducerName(nextLine[1]);

				producerDAO.save(producer);
			}

			String arqStudio = "src/main/resources/studio.csv";

			fis = new FileInputStream(new File(arqStudio));
			reader = new CSVReader(new InputStreamReader(fis));
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				Studio studio = new Studio();
				studio.setStudioName(nextLine[1]);

				studioDAO.save(studio);
			}

			String arqMovie = "src/main/resources/movie.csv";

			fis = new FileInputStream(new File(arqMovie));
			reader = new CSVReader(new InputStreamReader(fis));
			reader.readNext();
			ArrayList<Long> listProducers = new ArrayList<Long>();
			ArrayList<Long> listStudios = new ArrayList<Long>();
			while ((nextLine = reader.readNext()) != null) {

				Movie movie = new Movie();
				movie.setMovieTitle(nextLine[1]);
				movie.setWinner(Boolean.parseBoolean(nextLine[2]));
				movie.setYear(Integer.parseInt(nextLine[3]));

				listProducers.add(Long.parseLong(nextLine[4]));
				listProducers.add(Long.parseLong(nextLine[5]));

				listStudios.add(Long.parseLong(nextLine[6]));
				listStudios.add(Long.parseLong(nextLine[7]));

				List<Producer> producers = producerDAO.findAll(listProducers);
				List<Studio> studios = studioDAO.findAll(listStudios);

				movie.setProducers(producers);
				movie.setStudios(studios);

				movieDAO.save(movie);

				listProducers.clear();
				listStudios.clear();
			}
			reader.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(ArquivoService.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ArquivoService.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(ArquivoService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return "Arquivo Carregado!";
	}

	@Override
	public List<Movie> findAll() {

		if (carregarArquivo) {
			carregar();
			carregarArquivo = false;
		}

		return movieDAO.findAll();
	}

	@Override
	public List<Movie> findByYear(Integer year) {
		if (carregarArquivo) {
			carregar();
			carregarArquivo = false;
		}

		return movieDAO.findMovieByYear(year);
	}

	@Override
	public List<DTOQueryDuplicatedYear> findDuplicatedYears() {
		if (carregarArquivo) {
			carregar();
			carregarArquivo = false;
		}
		return movieDAO.findDuplicatedYears();
	}

	@Override
	public List<DTOQueryStudiosOnOrder> findStudiosOnOrder() {
		if (carregarArquivo) {
			carregar();
			carregarArquivo = false;
		}
		return studioDAO.findStudiosOnOrder();
	}

	@Override
	public List<DTOQueryWinnersProducers> findWinnersProducers() {
		if (carregarArquivo) {
			carregar();
			carregarArquivo = false;
		}
		List<DTOQueryWinnersProducers> list = producerDAO.findWinnersProducers();
		List<DTOQueryWinnersProducers> listObjects = new ArrayList<DTOQueryWinnersProducers>();

		if (!list.isEmpty()) {

			listObjects.add(list.get(0));
			listObjects.add(list.get(list.size() - 1));
		}
		return listObjects;
	}

	@Override
	public String deleteMovie(Long id) {
		if (carregarArquivo) {
			carregar();
			carregarArquivo = false;
		}

		Movie movie = movieDAO.getOne(id);

		if (movie.isWinner()) {
			return "Não é Possivel Deletar Filmes Vencedores!";
		} else {
			movieDAO.delete(id);
		}
		return "Filme Deletado Com Sucesso!";
	}
}