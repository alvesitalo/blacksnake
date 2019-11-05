package snake;

public class Kitty extends Snake {
	public Kitty(int[] posX , int[] posY, int length) {
		name = "kitty";
		powerHitWalls = true;
		powerGainDoublePoints = false;
		powerCrossBoundaries = true;
		alive = true;
		setLength(length);
		initPosition(posX, posY);
	}
	
	public void initPosition(int[] posX , int[] posY) {
		setXCoords(posX);
		setYCoords(posY);
	}
}
