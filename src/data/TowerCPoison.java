package data;

import java.util.concurrent.CopyOnWriteArrayList;


public class TowerCPoison extends Tower{

	
	public TowerCPoison(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies){
		super(type, startTile, enemies);
	}
	
	@Override
	public void shoot(Enemy target){
		super.projectiles.add(new ProjectilePoison(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		//as soon as shooting reduce hiddenHealth
		super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}
}
