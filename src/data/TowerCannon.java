 package data;

import static helpers.Artist.*;
import static helpers.Clock.Delta;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon {
	
	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, range;
	private Texture baseTexture, cannonTexture;
	private ArrayList<Projectile> projectiles;
	private CopyOnWriteArrayList<Enemy> enemies;
	private Enemy target;
	private boolean targeted;//to show if we have target
	
	//Texture		Tile		Damage		List of all the enemies to spot
	public TowerCannon (Texture baseTexture, Tile startTile, int damage, int range, CopyOnWriteArrayList<Enemy> enemies){
		this.baseTexture = baseTexture;
		this.cannonTexture = QuickLoad("FireTurret64");
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = (int) startTile.getWidth();
		this.height = (int) startTile.getHeight();
		this.range = range;
		this.firingSpeed = 3;	//time between two shots
		this.timeSinceLastShot = 0;
		this.projectiles = new ArrayList<Projectile>();
		this.enemies = enemies;
		this.targeted = false;
	}
	
	//decides witch enemy is the specific target for the tower 
	private Enemy acquireTarget(){
		Enemy closest = null;
		//arbitrary closest distance, after first loop, closestDistance change
		float closestDistance = 10000;
		for(Enemy e : enemies){
			if(isInRange(e) && findDistance(e) < closestDistance){
				closestDistance = findDistance(e);
				closest = e;
			}
		}
		//if there are no enemies in range
		if(closest != null){
			targeted = true;
		}
		return closest;
	}
	
	private boolean isInRange(Enemy e){
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if(xDistance < range && yDistance < range){
			return true;
		}
		return false;
	}
	
	private float findDistance(Enemy e){
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}
	
	
	private float calculateAngle(){
		
		/*Math.atan2() - Returns the angle Theta from the conversion 
		of rectangular coordinates (x, y) to polar coordinates (r, Theta).*/
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		
		//translate from polar to degrees (in double cast to float)
		return (float) Math.toDegrees(angleTemp) - 90;
	}
	
	public void updateEnemyList(CopyOnWriteArrayList<Enemy> newList){
		enemies = newList;
	}
	
	private void shoot(){
		timeSinceLastShot = 0;
	}
	
	public void update(){	
		//every time we update if there is no target we will acquire new one
		if(!targeted){
			target = acquireTarget();
		}
		//remove target if last target !isAlive
		if(target == null || target.isAlive() == false){
			targeted = false;
		}
		
		timeSinceLastShot += Delta();
		//when timeSinceLastShot surpass firingSpeed, shoot
		if(timeSinceLastShot > firingSpeed){
			shoot();
		}
		for(Projectile p : projectiles){
			p.update();
		}
		angle = calculateAngle();// correct angle at updating
		draw();
	}
	
	public void draw(){			//same as enemy.draw() method
		//draw tower base
		DrawQuadTex(baseTexture, x, y, width, height);
		//draw rotating cannon turret
		DrawQuadTexRot(cannonTexture, x, y, width, height, angle);
	}
}
