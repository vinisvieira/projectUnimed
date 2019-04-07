package com.project.unimed.service;

import java.util.List;

import com.project.unimed.dto.DTOQueryDuplicatedYear;
import com.project.unimed.dto.DTOQueryStudiosOnOrder;
import com.project.unimed.dto.DTOQueryWinnersProducers;
import com.project.unimed.model.Movie;

public interface IArquivoService {

	public String carregar();

	public List<Movie> findAll();

	public List<Movie> findByYear(Integer year);

	public List<DTOQueryDuplicatedYear> findDuplicatedYears();

	public List<DTOQueryStudiosOnOrder> findStudiosOnOrder();

	public List<DTOQueryWinnersProducers> findWinnersProducers();

	public String deleteMovie(Long id);
}