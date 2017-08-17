package data;

public class EnemyCar extends Enemy{

	public EnemyCar(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture("Car64");
		this.setSpeed(90);
		this.setHealth(125);
		this.setBounty(7);
	}
}
