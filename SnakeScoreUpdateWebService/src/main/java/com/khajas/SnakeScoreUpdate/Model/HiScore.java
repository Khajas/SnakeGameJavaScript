package com.khajas.SnakeScoreUpdate.Model;

public class HiScore {
	
	private String newScore;
	private String token;
	private String username;
	public HiScore() {
		super();
	}
	public HiScore(String newScore, String token, String username) {
		super();
		this.newScore = newScore;
		this.token = token;
		this.username = username;
	}
	public String getNewScore() {
		return newScore;
	}
	public String getToken() {
		return token;
	}
	public String getUsername() {
		return username;
	}
	public void setNewScore(String newScore) {
		this.newScore = newScore;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
