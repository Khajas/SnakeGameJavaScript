package com.khajas.SnakeScoreUpdate.Model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HiScore {
	private String token;
	private String newScore;
	public HiScore(){
		
	}
	public HiScore(String token, String newScore) {
		super();
		this.token = token;
		this.newScore = newScore;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNewScore() {
		return newScore;
	}
	public void setNewScore(String newScore) {
		this.newScore = newScore;
	}
}
