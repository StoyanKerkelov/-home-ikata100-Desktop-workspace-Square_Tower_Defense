package data;

public class EnemyZombie extends Enemy{

	public EnemyZombie(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture("ZombieNew64");
		this.setSpeed(40);
		this.setHealth(65);
		this.setBounty(4);
	}
}
