package data;


public class ProjectileIceball extends Projectile{

	public ProjectileIceball(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
	}

	@Override
	public void damage(){
		super.damage();
		if(super.getTarget().isFrosted()){
		} else {
			super.getTarget().setFrosted(true);
			super.getTarget().setSpeed(getTarget().getSpeed() * 0.4f);	//reduce only once speed by 40%
		}
	}
}
