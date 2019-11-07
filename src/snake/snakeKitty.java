package snake;

public class snakeKitty extends Snake {
	public snakeKitty(Snake s) {
		name = "kitty";
		length = s.getLength();
		powerHitWalls = true;
		powerGainDoublePoints = false;
		powerCrossBoundaries = true;
		alive = true;

		initPosition(s.getXCoords(), s.getYCoords());
		
		left = s.isLeft();
		right = s.isRight();
		up = s.isUp();
		down = s.isDown();
	}
	
	public void initPosition(int[] coordsX , int[] coordsY) {
		setXCoords(coordsX);
		setYCoords(coordsY);
	}
}
