package snake;

public class kittySnake extends Snake {
	public kittySnake(Snake s) {
		this.name = "kitty";
		this.length = s.getLength();
		this.powerHitEnemies = true;
		this.powerGainDoublePoints = false;
		this.powerCrossBoundaries = false;
		this.alive = true;
		this.direction = s.getDirection();

		initPosition(s.getXCoords(), s.getYCoords());
	}
	
	public void initPosition(int[] coordsX , int[] coordsY) {
		setXCoords(coordsX);
		setYCoords(coordsY);
	}
}
