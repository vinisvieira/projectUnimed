package com.project.unimed.dto;

public class DTOQueryDuplicatedYear {

	private int year;
	private Long winnerCount;
	
	public DTOQueryDuplicatedYear(int year, Long winnerCount) {
	
		this.year = year;
		this.winnerCount = winnerCount;
		
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Long getWinnerCount() {
		return winnerCount;
	}
	public void setWinnerCount(Long winnerCount) {
		this.winnerCount = winnerCount;
	}
	
	@Override
	public String toString() {
		return "DTOQueryDuplicatedYear [year=" + year + ", winnerCount=" + winnerCount + "]";
	}	
}