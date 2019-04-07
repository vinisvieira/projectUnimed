package com.project.unimed.test;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.unimed.dao.MovieDAO;
import com.project.unimed.dao.ProducerDAO;
import com.project.unimed.dao.StudioDAO;
import com.project.unimed.dto.DTOQueryDuplicatedYear;
import com.project.unimed.dto.DTOQueryStudiosOnOrder;
import com.project.unimed.dto.DTOQueryWinnersProducers;
import com.project.unimed.model.Movie;
import com.project.unimed.model.Producer;
import com.project.unimed.model.Studio;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

	@Autowired
	private MovieDAO movieDAO;

	@Autowired
	private ProducerDAO producerDAO;

	@Autowired
	private StudioDAO studioDAO;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void findByAgeShouldListData() {

		Movie movie = new Movie();
		movie.setMovieTitle("O Vencedor");
		movie.setWinner(true);
		movie.setYear(2002);

		Movie movie2 = new Movie();
		movie2.setMovieTitle("O Perdedor");
		movie2.setWinner(true);
		movie2.setYear(2005);

		movieDAO.save(movie);
		movieDAO.save(movie2);

		List<Movie> movies = movieDAO.findMovieByYear(2002);

		Assertions.assertThat(!movies.isEmpty());
		Assertions.assertThat(movies.size() == 1);
	}

	@Test
	public void findByDuplicateYearShouldListData() {

		Movie movie = new Movie();
		movie.setMovieTitle("O Vencedor");
		movie.setWinner(true);
		movie.setYear(2002);

		Movie movie2 = new Movie();
		movie2.setMovieTitle("O Perdedor");
		movie2.setWinner(true);
		movie2.setYear(2002);

		movieDAO.save(movie);
		movieDAO.save(movie2);

		List<DTOQueryDuplicatedYear> dtoQueryDuplicatedYear = movieDAO.findDuplicatedYears();

		Assertions.assertThat(!dtoQueryDuplicatedYear.isEmpty());
		Assertions.assertThat(dtoQueryDuplicatedYear.size() == 1);
	}
	
	@Test
	public void findStudiosOnOrderShouldListData() {
		
		ArrayList<Studio> studios = new ArrayList<Studio>();
		ArrayList<Studio> studios2 = new ArrayList<Studio>();
		
		Studio studio = new Studio();
		studio.setStudioName("Universal");
		
		studioDAO.save(studio);
		
		studios.add(studio);
		
		Movie movie = new Movie();
		movie.setMovieTitle("O Vencedor");
		movie.setWinner(true);
		movie.setYear(2002);
		movie.setStudios(studios);

		movieDAO.save(movie);
		
		Movie movie2 = new Movie();
		movie2.setMovieTitle("O Perdedor");
		movie2.setWinner(true);
		movie2.setYear(2002);
		movie2.setStudios(studios);

		movieDAO.save(movie2);
		
		Studio studio1 = new Studio();
		studio1.setStudioName("Disney");
		
		studioDAO.save(studio1);
		
		studios2.add(studio1);
		
		Movie movie3 = new Movie();
		movie3.setMovieTitle("O Lutador");
		movie3.setWinner(true);
		movie3.setYear(2006);
		movie3.setStudios(studios2);

		movieDAO.save(movie3);

		List<DTOQueryStudiosOnOrder> dtoQueryStudiosOnOrder = studioDAO.findStudiosOnOrder();

		Assertions.assertThat(!dtoQueryStudiosOnOrder.isEmpty());
		Assertions.assertThat(dtoQueryStudiosOnOrder.size() == 2);
	}

	@Test
	public void findWinnersProducersShouldListData() {
		
		ArrayList<Producer> producers = new ArrayList<Producer>();
		ArrayList<Producer> producers2 = new ArrayList<Producer>();
		
		Producer producer = new Producer();
		producer.setProducerName("Roberto");
		
		producerDAO.save(producer);
		
		producers.add(producer);
		
		Movie movie = new Movie();
		movie.setMovieTitle("O Vencedor");
		movie.setWinner(true);
		movie.setYear(2000);
		movie.setProducers(producers);

		movieDAO.save(movie);
		
		Movie movie2 = new Movie();
		movie2.setMovieTitle("O Perdedor");
		movie2.setWinner(true);
		movie2.setYear(2002);
		movie2.setProducers(producers);

		movieDAO.save(movie2);
		
		Producer producer2 = new Producer();
		producer2.setProducerName("Dennis");
		
		producerDAO.save(producer2);
		
		producers2.add(producer2);
		
		Movie movie3 = new Movie();
		movie3.setMovieTitle("O Lutador");
		movie3.setWinner(true);
		movie3.setYear(2000);
		movie3.setProducers(producers2);

		movieDAO.save(movie3);
		
		Movie movie4 = new Movie();
		movie4.setMovieTitle("O Magico");
		movie4.setWinner(true);
		movie4.setYear(2018);
		movie4.setProducers(producers2);

		movieDAO.save(movie4);

		List<DTOQueryWinnersProducers> dtoQueryWinnersProducers = producerDAO.findWinnersProducers();

		Assertions.assertThat(!dtoQueryWinnersProducers.isEmpty());
		Assertions.assertThat(dtoQueryWinnersProducers.size() == 2);
	}
	
	@Test
	public void deleteShouldRemoveData() {

		Movie movie = new Movie();
		movie.setMovieTitle("O Vencedor");
		movie.setWinner(false);
		movie.setYear(2019);

		movieDAO.save(movie);
		movieDAO.delete(movie);

		Assertions.assertThat(movieDAO.findOne(movie.getId())).isNull();

	}
}
