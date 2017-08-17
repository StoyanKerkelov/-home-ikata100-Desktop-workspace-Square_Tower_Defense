package data;

public class ProjectileBallistic extends Projectile{

	public ProjectileBallistic(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
	}

	@Override
	public void damage(){
		super.damage();
	}
}
