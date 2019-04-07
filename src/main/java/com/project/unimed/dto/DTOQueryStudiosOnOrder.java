package com.project.unimed.dto;

public class DTOQueryStudiosOnOrder {

	private String name;
	private Long winCount;

	public DTOQueryStudiosOnOrder(String name, Long winCount) {
		this.name = name;
		this.winCount = winCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getWinCount() {
		return winCount;
	}

	public void setWinCount(Long winCount) {
		this.winCount = winCount;
	}

	@Override
	public String toString() {
		return "DTOQueryStudiosOnOrder [name=" + name + ", winCount=" + winCount + "]";
	}

}
