package com.app.entities;

public class Candidate {
	private int canId;
	private String canName;
	private String party;
	private int votes;
	public Candidate(int canId, String canName, String party, int votes) {
		super();
		this.canId = canId;
		this.canName = canName;
		this.party = party;
		this.votes = votes;
	}
	public int getCanId() {
		return canId;
	}
	public void setCanId(int canId) {
		this.canId = canId;
	}
	public String getCanName() {
		return canName;
	}
	public void setCanName(String canName) {
		this.canName = canName;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	@Override
	public String toString() {
		return "Candidate [canId=" + canId + ", canName=" + canName + ", party=" + party + ", votes=" + votes + "]";
	} 
	
	
}
