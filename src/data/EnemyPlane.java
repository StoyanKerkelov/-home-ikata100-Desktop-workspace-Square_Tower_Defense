package data;

public class EnemyPlane extends Enemy{

	public EnemyPlane(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture("Plane64");
		this.setSpeed(160);
		this.setHealth(145);
		this.setBounty(12);
	}
}
