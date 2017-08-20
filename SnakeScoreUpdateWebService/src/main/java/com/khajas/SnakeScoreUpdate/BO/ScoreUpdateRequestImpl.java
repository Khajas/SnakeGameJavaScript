package com.khajas.SnakeScoreUpdate.BO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.khajas.SnakeScoreUpdate.DAO.ScoreUpdateService;
import com.khajas.SnakeScoreUpdate.DAO.ScoreUpdateServiceImpl;
import com.khajas.SnakeScoreUpdate.Model.HiScore;

@Path("/score")
public class ScoreUpdateRequestImpl implements ScoreUpdateRequest {
	
	private ScoreUpdateService sus;		
	public ScoreUpdateRequestImpl(){
		this.sus=new ScoreUpdateServiceImpl();
	}
		
	@Path("/getscore")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getScore() {	
		return Response.ok()
				.entity(sus.getHiScore())
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept")
				.allow("OPTIONS")
				.build();
	}

	@Path("/updatescore")
	@OPTIONS
	public Response getOptions() {	
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept")
				.allow("POST")
				.build();
	}
	
	@Path("/updatescore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateScore(HiScore newScore) {
		System.out.println("Update Called");
		sus.updateHiScore(newScore);
		return Response.ok()
	            .entity(newScore.getNewScore())
	            .header("Access-Control-Allow-Origin","*")
	            .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT")
	            .header("Access-Control-Allow-Credentials", "true")
	            .allow("OPTIONS")
	            .build();
	}
}
