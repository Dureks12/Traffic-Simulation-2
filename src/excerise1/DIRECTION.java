package excerise1;

public enum DIRECTION {
	NORTH(180,270,0, 0, -45),
	SOUTH(0,90,1, 0, 45),
	WEST(90,0,2, -45, 0),
	EAST(270,180,3,45, 0);
	
	private int lightRotation;
	private int rotation;
	private int arrayPos;
	private int x;
	private int y;
	
	private DIRECTION(int lightRotation, int rotation, int arrayPos, int x, int y){
		this.lightRotation = lightRotation;
		this.rotation = rotation;
		this.arrayPos = arrayPos;
		this.x = x;
		this.y = y;
	}
	
	public int getLightRotation(DIRECTION dir){
		return lightRotation;
	}
	public int getArrayPos(DIRECTION dir){
		return arrayPos;
	}
	public int setRotation(DIRECTION dir){
		return rotation;
	}
	
	public int getX(DIRECTION dir){
		return x;
	}
	
	public int getY(DIRECTION dir){
		return y;
	}

}
