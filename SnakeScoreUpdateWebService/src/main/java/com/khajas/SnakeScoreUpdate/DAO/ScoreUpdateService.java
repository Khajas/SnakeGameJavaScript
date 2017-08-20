package com.khajas.SnakeScoreUpdate.DAO;

import com.khajas.SnakeScoreUpdate.Model.HiScore;

public interface ScoreUpdateService {
	public String generateToken();
	public HiScore getHiScore();
	public void updateHiScore(HiScore newScore);
}