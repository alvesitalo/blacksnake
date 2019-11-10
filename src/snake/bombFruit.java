package snake;

public class bombFruit extends Fruit {
	bombFruit() {
		name = "bomb";
		setIcon();
		points = 0;
		initCoords();
		randCoords();
	}
	
	bombFruit(Fruit f) {
		name = "bomb";
		setIcon();
		points = 0;
		setXPos(f.getXPos());
		setYPos(f.getYPos());
	}
}