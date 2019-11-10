package snake;

public class starSnake extends Snake {
	public starSnake(Snake s) {
		name = "star";
		length = s.getLength();
		powerHitEnemies = false;
		powerGainDoublePoints = true;
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
