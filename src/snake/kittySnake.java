package snake;

public class kittySnake extends Snake {
	public kittySnake(Snake s) {
		name = "kitty";
		length = s.getLength();
		powerHitEnemies = true;
		powerGainDoublePoints = false;
		powerCrossBoundaries = false;
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
