package data;

import static helpers.Artist.*;

import org.newdawn.slick.opengl.Texture;

public enum TowerType {

	//enums: Tower(<Texture Base>, <Texture moving Gun> , 							<projectileType>, <damage>, <range>,<firingSpeed>, <cost>)
	
	TowerGun(new Texture[]{QuickLoad("TBaseMachineGun64"), QuickLoad("MachineGunTurret64")}, ProjectileType.Bullets, 2, 200, 0.5f, 1100f, 24),
	TowerFire(new Texture[]{QuickLoad("TBFireTower64"), QuickLoad("FireTurret64")}, ProjectileType.FireBall, 8, 450, 1.0f, 1000f, 28),
	TowerIce(new Texture[]{QuickLoad("TBaseFrostTower64"), QuickLoad("FrostTurret64")}, ProjectileType.SnowBall, 30, 250, 1.5f, 1000f, 55),
	TowerPoison(new Texture[]{QuickLoad("TBasePoisonTower64"), QuickLoad("PoisonTurret64")}, ProjectileType.PoisonBall, 1, 450, 1.6f, 650f, 45),
	TowerRocket(new Texture[]{QuickLoad("TBaseRocketTower64"), QuickLoad("RocketTurret64")}, ProjectileType.Rocket, 75, 1500, 2.2f, 600f, 90);

	Texture[] textures;	//Texture[0] = TowerBase, Texture[1] = TowerCannon
	ProjectileType projectileType;
	int range, cost;
	float firingSpeed;
	
	
	TowerType(Texture[] textures, ProjectileType projectileType, int damage, int range, float firingSpeed, float projectileSpeed, int cost){
		this.textures = textures;
		this.projectileType = projectileType;
		projectileType.setDamage(damage);
		projectileType.setSpeed(projectileSpeed);
		this.range = range;
		this.firingSpeed = firingSpeed;
		this.cost = cost;
	}
}
