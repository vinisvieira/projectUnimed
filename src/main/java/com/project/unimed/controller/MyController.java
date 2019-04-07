package com.project.unimed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.unimed.dto.DTOQueryDuplicatedYear;
import com.project.unimed.dto.DTOQueryStudiosOnOrder;
import com.project.unimed.dto.DTOQueryWinnersProducers;
import com.project.unimed.model.Movie;
import com.project.unimed.service.IArquivoService;

@RequestMapping("/rest")
@RestController
public class MyController {

	@Autowired
	private IArquivoService arquivoService;

	@RequestMapping("/load-files")
	public String carregarAquivos() {

		return arquivoService.carregar();
	}

	@RequestMapping("/movies")
	public List<Movie> listMovie() {
		return arquivoService.findAll();
	}

	@RequestMapping(value = "movies/{year}", method = RequestMethod.GET)
	public List<Movie> listMoviesByYear(@PathVariable("year") Integer year) {
		return arquivoService.findByYear(year);
	}

	@RequestMapping("/year/winners")
	public List<DTOQueryDuplicatedYear> findDuplicatedYears() {
		return arquivoService.findDuplicatedYears();

	}

	@RequestMapping("/studios/order")
	public List<DTOQueryStudiosOnOrder> findStudiosOnOrder() {
		return arquivoService.findStudiosOnOrder();
	}
	
	@RequestMapping("/producers/winners")
	public List<DTOQueryWinnersProducers> findWinnersProducers() {
		return arquivoService.findWinnersProducers();
	}
	
	@RequestMapping(value = "movies/delete/{id}", method = RequestMethod.GET)
	public String deleteMovie(@PathVariable("id") Long id) {
		return arquivoService.deleteMovie(id);
	}
}