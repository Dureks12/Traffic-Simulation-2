package excerise1;

import java.awt.Color;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Roads extends Actor {
	
	private static final int WORLD_WIDTH = 1000;
	private static final int WORLD_HEIGHT = 750;
	private static final int PIXELS = 50;
	
	private GreenfootImage nsBound = new GreenfootImage(PIXELS, WORLD_HEIGHT);
	private GreenfootImage weBound = new GreenfootImage(WORLD_WIDTH, PIXELS);
	private GreenfootImage road;
	
	public Roads(DIRECTION dir){
	    road = (dir == DIRECTION.NORTH || dir == DIRECTION.SOUTH)? nsBound:weBound;
	    road.setColor(Color.GRAY);
	    road.fill();
	    setImage(road);
	 }
	
	public GreenfootImage getRoad(){
		return road;
	}
	
}
