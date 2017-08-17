package data;


public class EnemyFlyingSaucer extends Enemy{

	public EnemyFlyingSaucer(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture("UFO64");
		this.setSpeed(170);
		this.setHealth(220);
		this.setBounty(20);
	}
}
