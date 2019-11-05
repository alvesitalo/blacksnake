package snake;

public class Star extends Snake {
	public Star() {
		name = "star";
		powerHitWalls = true;
		powerGainDoublePoints = true;
		powerCrossBoundaries = true;
		alive = true;
	}
}
