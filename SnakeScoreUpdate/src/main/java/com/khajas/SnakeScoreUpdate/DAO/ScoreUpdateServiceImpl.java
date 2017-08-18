package com.khajas.SnakeScoreUpdate.DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Scanner;

import org.apache.commons.lang.RandomStringUtils;

import com.khajas.SnakeScoreUpdate.Model.HiScore;

public class ScoreUpdateServiceImpl implements ScoreUpdateService{
	private HashSet<String> validTokens=new HashSet<>();
	
	@Override
	public String generateToken() {
		return RandomStringUtils.randomAlphanumeric(20).toUpperCase();
	}

	@Override
	public HiScore getHiScore() {
		String score=this.readCurrentScore();
		String token=this.generateToken();
		validTokens.add(token);
		if(validTokens.size()>10)
			validTokens.clear();
		return new HiScore(token, score);
	}

	@Override
	public void updateHiScore(HiScore newScore) {
		System.out.println("Called");
		this.validTokens.add("abcd");
		if(this.validTokens.contains(newScore.getToken())){
			System.out.println(newScore.getToken());
			this.validTokens.remove(newScore.getToken());
			System.out.println(newScore.getNewScore());
			this.updateScore(newScore.getNewScore());
		}
	}
	
	private String readCurrentScore(){
		String score="0";
		try{
			Scanner sc=new Scanner(new File("C:\\Users\\Anwar\\Desktop\\score.txt"));
			while(sc.hasNext()){
				score=sc.nextLine();
				System.out.println(score);
			}
			sc.close();
		}catch(IOException e){
			System.out.println("Error Fetching the high score data");
		}
		return score;
	}
	
	private void updateScore(String newHiScore){
		try(Writer writer=new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("C:\\Users\\Anwar\\Desktop\\score.txt"),"utf-8"))){
			writer.write(newHiScore);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
