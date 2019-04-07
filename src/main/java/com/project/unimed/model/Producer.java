package com.project.unimed.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "producer")
@EntityListeners(AuditingEntityListener.class)
public class Producer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String producerName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	@Override
	public String toString() {
		return "Producer [id=" + id + ", producerName=" + producerName + "]";
	}

}
