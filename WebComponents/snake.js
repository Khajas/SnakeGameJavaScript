function Snake(){

	this.x=0;
	this.y=0;
	this.xspeed=1;
	this.yspeed=0;
	this.length=0;
	this.tail=[];
		
	this.dir = function(x,y){
		this.xspeed=x;
		this.yspeed=y;
	}
	
	this.eat = function(foodPos){
		var d =dist(this.x, this.y, foodPos.x, foodPos.y);
		if(d<1){
			this.length++;
			score=this.length;
			text(score, 500,0,10, 30);
			frameRate(++fRate);
			return true;
		}
		return false;
	}
	
	this.death = function(){
		for(var i = 0; i <this.tail.length;++i){
			var pos=this.tail[i];
			var d = dist(this.x, this.y, pos.x, pos.y);
			if(d<1){
				this.length=0;
				this.tail=[];
				this.updateHighScore();
				preload();
				draw();
				score=0;
				fRate=10;
				frameRate(fRate);
			}
		}
	}
	
	this.updateHighScore = function(){
		if(hiscore<score){
			hiscore=score;
		}else{
			hiscore=-1;
			return;
		}
		console.log(hiscore);
		updateScore_json.newScore=""+hiscore;
		updateScore_json.token=token;
		var url="http://52.87.171.153:8080/SnakeScoreUpdate/webapi/score/updatescore";
		httpPost(url,"json",updateScore_json,false);
	}
	
	this.update = function(){
		if(this.length === this.tail.length){						
			for(var i=0; i<this.tail.length-1;i++){
				this.tail[i]=this.tail[i+1];
			}
		}
		this.tail[this.length-1]=createVector(this.x, this.y);
		this.x=this.x+this.xspeed*scl;
		this.y=this.y+this.yspeed*scl;
		
		this.x=constrain(this.x, 0, width-scl);
		this.y=constrain(this.y, borders, height-scl);
	}
	
	this.show =function(){
		fill(255);
		strokeWeight(0);
		for(var i=0; i < this.tail.length;++i){
			rect(this.tail[i].x, this.tail[i].y, scl, scl );
		}
		rect(this.x, this.y, scl,scl);
	}
	
	this.boundry = function(){
		strokeWeight(10);
		stroke(125);
		line(0,0,0, height);	// Vertical Left
		line(0,60,width,60);		// Horizontal top	
		line(0, height, width,width);	//	Horizontal bottom
		line(height, 0,width, width);	// Vertical right
		strokeWeight(0);
	}
}
