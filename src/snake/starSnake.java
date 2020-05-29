package snake;

public class starSnake extends Snake {
	public starSnake(Snake s) {
		this.name = "star";
		this.length = s.getLength();
		this.powerHitEnemies = false;
		this.powerGainDoublePoints = true;
		this.powerCrossBoundaries = true;
		this.alive = true;
		this.direction = s.getDirection();

		initPosition(s.getXCoords(), s.getYCoords());
	}

	public void initPosition(int[] coordsX , int[] coordsY) {
		setXCoords(coordsX);
		setYCoords(coordsY);
	}
}
