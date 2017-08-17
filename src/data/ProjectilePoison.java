package data;


public class ProjectilePoison extends Projectile{

	public ProjectilePoison(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
	}

	@Override
	public void damage(){
		super.setDamage( (int) ( getTarget().getHealth() * 0.18f)); //hit with 18 % of total enemy health
		super.damage();
	}
}
