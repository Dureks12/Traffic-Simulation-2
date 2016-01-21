package excerise1;

import java.util.*;
import java.awt.Color;

import greenfoot.GreenfootImage;
import greenfoot.World;

public class TrafficWorld extends World {
	
	private static final Random rn = new Random();
	private static final int WORLD_HEIGHT = 750;
	private static final int WORLD_WIDTH = 1000;
	private static final int CELL_SIZE = 1;	
	private static final int N_WE_ROADS = 5;
	private static final int N_NS_ROADS = 7;
	private static final int ROAD_SIZE = 50;
	private static final int N_INTER = N_NS_ROADS * N_WE_ROADS;
	private static final int NS_SPACE = ROAD_SIZE + ((WORLD_WIDTH - (ROAD_SIZE * N_NS_ROADS))/N_NS_ROADS+17);
	private static final int WE_SPACE = ROAD_SIZE+ ((WORLD_HEIGHT - (ROAD_SIZE * N_WE_ROADS))/N_WE_ROADS+25);
	
	private GreenfootImage background = getBackground();
	private Roads[] eastWestBound = new Roads[N_WE_ROADS];
	private Roads[] northSouthBound = new Roads[N_NS_ROADS];
	private Intersection[] inter = new Intersection[N_INTER];
	
	public TrafficWorld(){
		
		super(WORLD_WIDTH, WORLD_HEIGHT, CELL_SIZE);	
		
		background.setColor(Color.GREEN);
		background.fill();
		
		fillRoads();
		createEastWestRoads();
		createNorthSouthRoads();
		createInterSections();
		drawNSCars();
		drawWECars();
		
	}
	
	private void fillRoads(){
		for(int ns = 0; ns < N_NS_ROADS; ns++){
			northSouthBound[ns] = new Roads(DIRECTION.NORTH);
		}
		
		for(int we = 0; we < N_WE_ROADS; we++){
			eastWestBound[we] = new Roads(DIRECTION.WEST);
		}
		
	}
	
	private void createInterSections(){
		int n = 0;
		for(int ns = 0; ns < N_NS_ROADS; ns++){
			for(int we = 0; we < N_WE_ROADS; we++, n++ ){
				int x = ((NS_SPACE*ns)+(ROAD_SIZE/2));
				int y = (ROAD_SIZE/2)+(WE_SPACE*we);
				inter[n] = new Intersection(x, y);
				addLights(inter[n]);
				addObject(inter[n],x,y);
			}
		}
	}
	
	private void addLights(Intersection inter){
		for(DIRECTION dir: DIRECTION.values()){
			int x = inter.getLight(dir).getXPos();
			int y = inter.getLight(dir).getYPos();
			addObject(inter.getLight(dir),x,y);
		}
	}
	
	private void createEastWestRoads(){
		for(int we = 0; we < N_WE_ROADS; we++){
			int y = (ROAD_SIZE/2)+(WE_SPACE*we);
			addObject(eastWestBound[we],WORLD_WIDTH/2, y);
			
		}
	}
	
	private void createNorthSouthRoads(){
		for(int ns = 0; ns < N_NS_ROADS; ns++){
			int x = ((NS_SPACE*ns)+(ROAD_SIZE/2));
			addObject(northSouthBound[ns], x, WORLD_HEIGHT/2);
		}
	}
	
	private void drawNSCars(){
		for(int car = 0; car < N_NS_ROADS; car++){
			int x = ((NS_SPACE*car)+(ROAD_SIZE/2));
			addCarsNorthSouth(x + (ROAD_SIZE/2), car, DIRECTION.NORTH);
			addCarsNorthSouth(x - (ROAD_SIZE/2), car, DIRECTION.SOUTH);
		}
	}
	
	private void drawWECars(){
		for(int car = 0; car < N_WE_ROADS; car++){
			int y = (ROAD_SIZE/2)+(WE_SPACE*car);
			addCarsWestEast(y, car, DIRECTION.WEST);
			addCarsWestEast(y,car, DIRECTION.EAST);
		}
	}
	
	private void addCarsNorthSouth(int x, int nCar, DIRECTION dir){
			Car car = new Car(dir);
			int cWidth = car.getImage().getWidth()/4;
			int xPos = (dir == DIRECTION.NORTH)? x-cWidth:(x+cWidth);
			int y = rn.nextInt(WORLD_HEIGHT-(cWidth*4))+(cWidth*4);
			addObject(car, xPos , y);
	}
	
	private void addCarsWestEast(int y, int nCar, DIRECTION dir){
		Car car = new Car(dir);
		int cWidth = car.getImage().getWidth()/4;
		int cHeight = car.getImage().getHeight()/2;
		int yPos = (dir == DIRECTION.WEST)? y+cWidth:y-cHeight;
		int x = rn.nextInt(WORLD_WIDTH - (cHeight*2)) + (cHeight*2); 
		addObject(car, x, yPos);
	}
	
}
