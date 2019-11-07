package snake;

public class snakeStar extends Snake {
	public snakeStar(Snake s) {
		name = "star";
		powerHitWalls = true;
		powerGainDoublePoints = true;
		powerCrossBoundaries = true;
		alive = true;
	}
}
