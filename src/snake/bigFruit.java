package snake;

public class bigFruit extends Fruit {
	bigFruit() {
		name = "big";
		setIcon();
		points = 4;
		initCoords();
		randCoords();
	}
	
	bigFruit(Fruit f) {
		name = "big";
		setIcon();
		points = 4;
		setXPos(f.getXPos());
		setYPos(f.getYPos());
	}
}