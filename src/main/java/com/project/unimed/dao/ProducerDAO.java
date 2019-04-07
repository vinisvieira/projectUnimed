package com.project.unimed.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.unimed.dto.DTOQueryWinnersProducers;
import com.project.unimed.model.Producer;

public interface ProducerDAO extends JpaRepository<Producer, Long> {
	
	@Query("select new com.project.unimed.dto.DTOQueryWinnersProducers(p.producerName, min(m.year), max(m.year), (max(m.year) - min(year)) as intervalo) from Movie m inner join m.producers p where m.winner = 1 group by p.producerName order by intervalo asc")
	public List<DTOQueryWinnersProducers> findWinnersProducers();
	
}