package excerise1;

import java.util.*; 
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor implements IntersectionListener {
	
	private static final int WORLD_HEIGHT = 750;
	private static final int WORLD_WIDTH = 1000;
	private static final int NORMAL_SPEED = 4;
	private static final int FAST_SPEED = 6;
	private static final int SLOW_SPEED = 1;
	private static final int N_CARS = 4;
	private static final int STOP = 0;

	
	private GreenfootImage[] cars = new GreenfootImage[N_CARS];
	private GreenfootImage currentCar;
	private Random rn = new Random();
	private DIRECTION dir;
	private int speed;
	
	public Car(DIRECTION dir){
		this.dir = dir;
		fillCars();
		setImage(setCar());
		rotate(dir);
	}
	
	private void fillCars(){
		cars[0] = new GreenfootImage("images\\topCarBlue.png");
		cars[1] = new GreenfootImage("images\\topCarRed.png");
		cars[2] = new GreenfootImage("images\\topCarPurple.png");
		cars[3] = new GreenfootImage("images\\topCarYellow.png");
	}
	
	private GreenfootImage setCar(){
		currentCar = cars[rn.nextInt(N_CARS)];
		return currentCar;
	}
	
	private void rotate(DIRECTION dir){
		setRotation(dir.setRotation(dir));
	}
	
	public GreenfootImage currentCar(){
		return currentCar;
	}
	
	private void turnAround(){
		
		int height = currentCar.getHeight();
		
		turnAround(DIRECTION.NORTH, getX(), WORLD_HEIGHT-height);
		turnAround(DIRECTION.SOUTH, getX(), height);
		turnAround(DIRECTION.WEST,height,getY());
		turnAround(DIRECTION.EAST,WORLD_WIDTH-height, getY());
		
	}
	
	private void turnAround(DIRECTION carDir, int x, int y ){
		if(this.isAtEdge() && dir == carDir){
			setLocation(x,y);
		}
		
	}
	
	public void act(){
		turnAround();
		move(speed);
	}

//	@Override
//	public void approaching(Intersection i) {
//		
//		
//	}
//
//	@Override
//	public void inInterection(Intersection i) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void leaving(Intersection i) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void approaching(Intersection i) {
		for(DIRECTION d: DIRECTION.values()){
			approachingInter(d, i);
		}
		
	}
	
	private void approachingInter( DIRECTION dir, Intersection i){
		if(this.dir == dir  &&  i.getLight(dir).getState() == Light.STATE.GREEN){
			speed = NORMAL_SPEED;
		}
		
		if(this.dir == dir && i.getLight(dir).getState() == Light.STATE.YELLOW){
			speed = SLOW_SPEED;
		}
		
		if(this.dir == dir && i.getLight(dir).getState() == Light.STATE.RED){
			speed = SLOW_SPEED;
		}
		
	}


	@Override
	public void inInterection(Intersection i) {
		for(DIRECTION d: DIRECTION.values()){	
			inInter(d,i);
		}
	}
	
	private void inInter(DIRECTION dir, Intersection i){ 
		Light.STATE state = i.getLight(dir).getState();
		if(this.dir == dir && isTouching(i.getClass()) && state == Light.STATE.RED){
			speed = STOP;
		}
		
		if(this.dir == dir && isTouching(i.getClass()) && state == Light.STATE.GREEN || state == Light.STATE.YELLOW){
			speed = FAST_SPEED;
		}
	}

	@Override
	public void leaving(Intersection i) {
		speed = NORMAL_SPEED;
	}
	
}
