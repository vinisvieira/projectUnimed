package com.project.unimed.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "studio")
@EntityListeners(AuditingEntityListener.class)
public class Studio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String studioName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudioName() {
		return studioName;
	}

	public void setStudioName(String studioName) {
		this.studioName = studioName;
	}

	@Override
	public String toString() {
		return "Studio [id=" + id + ", studioName=" + studioName + "]";
	}
}