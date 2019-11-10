package snake;

public class decreaseFruit extends Fruit {
	decreaseFruit() {
        name = "decrease";
        setIcon();
		points = 0;
		initCoords();
		randCoords();
	}
	
	decreaseFruit(Fruit f) {
        name = "decrease";
        setIcon();
		points = 0;
		setXPos(f.getXPos());
		setYPos(f.getYPos());
	}
}