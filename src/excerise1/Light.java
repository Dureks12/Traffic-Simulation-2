package excerise1;

import greenfoot.Actor;

public class Light extends Actor {
	
	public enum STATE{
		GREEN, YELLOW, RED;
	}
    
		
	private int x; 
	private int y;
	private DIRECTION dir;
	private STATE currentState;
	private String currentLight;
	private String[] lights = {"images\\trafficLightGreen.png", "images\\trafficLightYellow.png","images\\trafficLightRed.png"};
	
	public Light(DIRECTION dir, int x, int y){
		this.x = x;
		this.y = y;
		this.dir = dir;
		setRotation(dir.getLightRotation(dir));
		setLights();
	}

	private void setLights(){
		currentLight = (dir == DIRECTION.NORTH || dir == DIRECTION.SOUTH)? lights[STATE.GREEN.ordinal()]:lights[STATE.RED.ordinal()];
		currentState = (dir == DIRECTION.NORTH || dir == DIRECTION.SOUTH)? STATE.GREEN:STATE.RED;
		setImage(currentLight);
	}
		
	public void setColor(STATE state){
		setImage(lights[state.ordinal()]);
		currentState = state;
	}
	
	public int getXPos(){
		return x;
	}

	public int getYPos(){
		return y;
	}

	public STATE getState(){
		return currentState;
	}
	
}
