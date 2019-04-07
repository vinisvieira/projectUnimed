package com.project.unimed.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.unimed.dto.DTOQueryStudiosOnOrder;
import com.project.unimed.model.Studio;

public interface StudioDAO extends JpaRepository<Studio, Long> {

	@Query("select new com.project.unimed.dto.DTOQueryStudiosOnOrder(s.studioName, count(*)) from Movie m inner join m.studios s where m.winner = 1 group by s.studioName order by count(*) desc")
	public List<DTOQueryStudiosOnOrder> findStudiosOnOrder();
}
