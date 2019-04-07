package com.project.unimed.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.unimed.dto.DTOQueryDuplicatedYear;
import com.project.unimed.model.Movie;

public interface MovieDAO extends JpaRepository<Movie, Long> {

	@Query("select m from Movie m where m.winner = 1 and m.year = :year")
	public List<Movie> findMovieByYear(@Param("year") Integer year);

	@Query("select new com.project.unimed.dto.DTOQueryDuplicatedYear(m.year, count(*)) from Movie m where m.winner = 1 group by m.year having count(*) > 1")
	public List<DTOQueryDuplicatedYear> findDuplicatedYears();

}
