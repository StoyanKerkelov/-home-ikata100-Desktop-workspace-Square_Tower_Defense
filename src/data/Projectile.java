package data;

import org.newdawn.slick.opengl.Texture;


import static helpers.Clock.*;
import static helpers.Artist.*;

public abstract class Projectile implements Entity{

	private Texture texture;
	private float x, y, speed, xVelocity, yVelocity, angle;
	private int damage, width, height;
	private Enemy target;
	private boolean alive;
	
	public Projectile(ProjectileType type, Enemy target, float x, float y, int width, int height) {

		this.texture = type.texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = type.speed;
		this.damage = type.damage;
		this.target = target;
		this.alive = true;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		this.angle = 0f;
		calculateDirection();
	}
	
	private void calculateDirection(){
		
		float totalAllowedMovement = 1.0f;// = 1.0 using fractions
		float xDistanceFromTarget = Math.abs(target.getX() - x - TILE_SIZE / 4 + TILE_SIZE / 2);
		float yDistanceFromTarget = Math.abs(target.getY() - y - TILE_SIZE / 4 + TILE_SIZE / 2);
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
		xVelocity = xPercentOfMovement;
		yVelocity = totalAllowedMovement - xPercentOfMovement;
		
		//Set direction based on position of target relative to tower
	
		if(target.getX() < x){
			xVelocity *= -1;	//reverse velocity
		}
		if(target.getY() < y){
			yVelocity *= -1;	//reverse velocity
		}
	}
	
	//Deal damage to Enemy
	public void damage(){
		target.damage(damage);	//Enemy.damage(damage)
		alive = false;
	}
	
	private float calculateAngle(){
		
		/*Math.atan2() - Returns the angle Theta from the conversion 
		of rectangular coordinates (x, y) to polar coordinates (r, Theta).*/
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		
		//translate from polar to degrees (in double cast to float)
		return (float) Math.toDegrees(angleTemp) - 90;
	}
	
	public void update(){
		
		if(alive){
			calculateDirection();
			x += xVelocity * speed * Delta();
			y += yVelocity * speed * Delta();
			
			//Artist.CheckCollision
			if(CheckCollision(x, y, width, height, target.getX(), target.getY(),	
					target.getWidth(), target.getHeight())){
				damage();
			}
			angle = calculateAngle();// correct angle at updating
			draw();
		}
	}
	
	public void draw(){
		DrawQuadTexRot(texture, x, y, width, height, angle);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Enemy getTarget() {
		return target;
	}
	
	public void setAlive(boolean status) {
		alive = status;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
