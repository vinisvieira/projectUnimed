package com.project.unimed.dto;

public class DTOQueryWinnersProducers {

	private String producerName;
	private int interval;
	private int previousWin;
	private int followingWin;

	public DTOQueryWinnersProducers(String producerName, int previousWin, int followingWin, int interval) {
	
		this.producerName = producerName;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
		this.interval = interval;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getPreviousWin() {
		return previousWin;
	}

	public void setPreviousWin(int previousWin) {
		this.previousWin = previousWin;
	}

	public int getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(int followingWin) {
		this.followingWin = followingWin;
	}

	@Override
	public String toString() {
		return "DTOQueryWinnersProducers [producerName=" + producerName + ", interval=" + interval + ", previousWin="
				+ previousWin + ", followingWin=" + followingWin + "]";
	}

}
