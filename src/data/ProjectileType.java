package data;

import static helpers.Artist.QuickLoad;

import org.newdawn.slick.opengl.Texture;

public enum ProjectileType {

	//ProjectileType fields are set in TowerType

	Bullets(QuickLoad("ProjectileBullets32"), 1, 1),
	FireBall(QuickLoad("ProjectileFire32"), 1, 1),
	SnowBall(QuickLoad("ProjectileSnowFlake32"), 1, 1),
	PoisonBall(QuickLoad("ProjectilePoison32"), 1, 1),
	Rocket(QuickLoad("ProjectileRocket32"), 1, 1);
	
	Texture texture;
	int damage;
	float speed;
	
	ProjectileType(Texture texture, int damage, float speed){
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
