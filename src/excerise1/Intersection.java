package excerise1; 

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.util.*;

public class Intersection extends Actor{
	
	private static final int YELLOW_COUNT = 125;
	private static final int GREEN_COUNT = 200;
	private static final int RED_COUNT = 75;
	private static final int N_LIGHTS = 4;
	private static final int RADIUS = 100;
	private static final int IS_SIZE= 50;
	
	private static final GreenfootImage intersection = new GreenfootImage(IS_SIZE, IS_SIZE);
	
	private ArrayList<IntersectionListener> near = new ArrayList<IntersectionListener>();
	private ArrayList<IntersectionListener> inIntersection = new ArrayList<IntersectionListener>();
	private ArrayList<IntersectionListener> oldInInter = new ArrayList<IntersectionListener>();
	private ArrayList<IntersectionListener> leaving = new ArrayList<IntersectionListener>();
	private Light[] lights = new Light[N_LIGHTS];
	
	private int redCount = RED_COUNT;
	private int greenCount = GREEN_COUNT;
	private int yellowCount = YELLOW_COUNT;
	private int x;
	private int y; 
		
	public Intersection(int x, int y){
		this.x = x;
		this.y = y;
		setImage(intersection);
		fillLights();
	}
	
	private void fillLights(){
		
		for(DIRECTION d: DIRECTION.values()){
			int xPos = (x)+d.getX(d);
			int yPos = (y+d.getY(d));	
			lights[d.getArrayPos(d)] = new Light(d, xPos, yPos);	
		}
	}
	
	private void changeAllLights(){
		changeLight(DIRECTION.NORTH);
		changeLight(DIRECTION.WEST);
	}
	
	private void changeLight(DIRECTION dir){
		Light l = lights[dir.getArrayPos(dir)];
		switch(l.getState()){
		case GREEN:
			yellowCount--;
			changeToYellow(dir);
			break;
		case YELLOW:
			redCount--;
			changeToRed(dir);
			break;
		case RED:
			greenCount--;
			changeToGreen(dir);
			break;
		
		}
	}
	
	private void changeToGreen(DIRECTION dir){
		Light l = lights[dir.getArrayPos(dir)];
		Light lA = lights[dir.getArrayPos(dir) +1];
		if(greenCount == 0){
			l.setColor(Light.STATE.GREEN);
			lA.setColor(Light.STATE.GREEN);
			greenCount = GREEN_COUNT;
		}
	}
	
	private void changeToYellow(DIRECTION dir){
		Light l = lights[dir.getArrayPos(dir)];
		Light lA = lights[dir.getArrayPos(dir) +1];
		if(yellowCount == 0){
			l.setColor(Light.STATE.YELLOW);
			lA.setColor(Light.STATE.YELLOW);
			yellowCount = YELLOW_COUNT;
		}
	}
	
	private void changeToRed(DIRECTION dir){
		Light l = lights[dir.getArrayPos(dir)];
		Light lA = lights[dir.getArrayPos(dir) +1];
		if(redCount == 0){
			l.setColor(Light.STATE.RED);
			lA.setColor(Light.STATE.RED);
			redCount = RED_COUNT;
		}
	}
	
	private void inRange(){
		near = (ArrayList<IntersectionListener>) this.getObjectsInRange(RADIUS, IntersectionListener.class);
		for(IntersectionListener n: near){
			n.approaching(this);
		}
	}
	
	private void inIntersection(){	
		
		for(IntersectionListener n: near){
			if(isTouching(n.getClass())){
				inIntersection.add(n);
				n.inInterection(this);
			}
		}
	
	}
	
	private void leaving(){
		for(IntersectionListener n: oldInInter){
			if(!inIntersection.contains(n)){
				n.leaving(this);
			}
		}
	}
	
	
	public int getXPos(){
		return x;
	}
	
	public int getYPos(){
		return y;
	}
	
	public Light[] getAllLights(){
		return lights;
	}
	
	public Light getLight(DIRECTION dir){
		return lights[dir.getArrayPos(dir)];
	}
	
	public void act(){
		leaving();
		inRange();
		inIntersection();
		changeAllLights();
		
		oldInInter = inIntersection;
	}
	
}
