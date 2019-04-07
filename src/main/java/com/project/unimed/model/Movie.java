package com.project.unimed.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "movie")
@EntityListeners(AuditingEntityListener.class)
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int year;
	private String movieTitle;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "movie_has_studios", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = {
			@JoinColumn(name = "studio_id") })
	private List<Studio> studios;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "movie_has_producers", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = {
			@JoinColumn(name = "producer_id") })
	private List<Producer> producers;
	private boolean winner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public List<Studio> getStudios() {
		return studios;
	}

	public void setStudios(List<Studio> studios) {
		this.studios = studios;
	}

	public List<Producer> getProducers() {
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", year=" + year + ", movieTitle=" + movieTitle + ", studios=" + studios
				+ ", producers=" + producers + ", winner=" + winner + "]";
	}
}