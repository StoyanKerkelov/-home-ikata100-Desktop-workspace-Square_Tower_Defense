package data;

public class EnemyTank extends Enemy{

	public EnemyTank(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture("TankNew64");
		this.setSpeed(50);
		this.setHealth(420);
		this.setBounty(18);
	}
}
