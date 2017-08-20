var s;
var scl = 20;
var food;
var currentDir;
var borders=60;
var score=0;
var hiscore_json;
var hiscore=-1;
var token;

var foodColor=123;
var hue=10;
var sat=25;
var updateScore_json;

function preload() {
	var url="http://localhost:8080/SnakeScoreUpdate/webapi/score/getscore";
	hiscore_json=httpGet(url, "json",false,function(response){
		hiscore_json=response;
	});
}
// 52.87.171.153
function setup() {
	createCanvas(600,600);
	s=new Snake();
	frameRate(10);
	food=pickLocation();
	currentDir="RIGHT";
	updateScore_json={};
	updateScore_json.newScore="0";
	updateScore_json.token="UNDEFINED";
}

function draw() {
	if(!hiscore_json){
		setTimeout(function(){    
			document.getElementById("loader").style.center="-300px";
		},100)
		return;
	}
	else document.getElementById("loader").style.display="none";
	background(51);
	textSize(30);
	fill(250);
	text("Hi,", 5,0,10, 30);
	text("Anwar! ", 40,0,10, 30);
	text("Score: ", 400,0,10, 30);
	textAlign(LEFT);
	text(score, 500,0,10, 30);
	text("Hi-Score: ", 400,25,10, 30);
	if(hiscore===-1)
		hiscore=hiscore_json["newScore"];
	token=hiscore_json["token"];	
	text(hiscore, 525 ,25,10, 30);
	if(s.eat(food)){
		food=pickLocation();
		foodColor=randomizeColors();
		hue=randomizeColors();
		sat=randomizeColors();
	}
	s.death();
	s.update();
	s.show();
	s.boundry();
	fill(foodColor, hue, sat);
	rect(food.x, food.y, scl, scl);
	s.boundry();
}

function randomizeColors(){
	var color=floor(random(255));
	while(color=== 0 || color===255){
		color=floor(random(255));
	}
	return color;
}

function pickLocation(){
	var cols=floor(width/scl);
	var rows=floor(height/scl);
	var x=floor(random(cols))*scl;
	var y=floor(random(rows))*scl;
	if(y<=60) y+=borders;
	if(y>=600) y%=580;
	console.log("x ",x,"y ",y);
	food = createVector(x,y);
	console.log(food);
	return food;
}

function keyPressed(){
	if(keyCode === UP_ARROW){
		if(currentDir!== "DOWN"){
			s.dir(0,-1);
			currentDir="UP";
		}
	}else if(keyCode === DOWN_ARROW){
		if(currentDir!== "UP"){
			s.dir(0,1);
			currentDir="DOWN";
		}
	}else if(keyCode === RIGHT_ARROW){
		if(currentDir!== "LEFT"){
			s.dir(1,0);
			currentDir="RIGHT";
		}
	}else if(keyCode === LEFT_ARROW){
		if(currentDir!== "RIGHT"){
			s.dir(-1,0);
			currentDir="LEFT";
		}
	}
}