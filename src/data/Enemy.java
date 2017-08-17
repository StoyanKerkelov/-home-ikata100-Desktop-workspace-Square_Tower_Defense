package data;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.*;
import static helpers.Clock.*;

public class Enemy implements Entity{

	private int width, height, bounty, currentCheckpoint;
	//currentCheckpoint - keeps track on witch point of the maze from the beginning we are at
	private float speed, x, y, health, startHealth, hiddenHealth, angle;
	private Texture texture, healthBackground, healthForeground, healthBorder;
	private TileGrid grid;
	private Tile startTile;
	private boolean first, alive, frosted; // first update after boot
	//store Checkpoints
	private ArrayList<Checkpoint> checkpoints;
	//store directions
	private int[] directions;
	
	//Default constructor
	public Enemy(int tileX, int tileY, TileGrid grid){
		
		this.texture = QuickLoad("ZombieNew64");
		this.healthBackground = QuickLoad("HealthBackground");
		this.healthForeground = QuickLoad("HealthForeground");
		this.healthBorder = QuickLoad("HealthBorder");
		this.startTile = grid.getTile(tileX, tileY);
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = TILE_SIZE;
		this.height = TILE_SIZE;
		this.speed = 50;//default
		this.health = 50;//default
		this.startHealth = health;
		this.hiddenHealth = health;
		this.angle = 0;
		this.grid = grid;
		this.first = true;
		this.alive = true;
		this.frosted = false;
		this.checkpoints = new ArrayList<Checkpoint>();
		this.directions = new int[2];
		this.directions[0] = 0;
		this.directions[1] = 0;
		directions = findNextD(startTile);
		this.currentCheckpoint = 0;
		populateCheckpointList();
		this.bounty = 0;
	}
	
	
	//constructor used in wave
	public Enemy(Texture texture, Tile startTile, TileGrid grid,
			int width,  int height, float speed, float health, int bounty) {
	this.texture = texture;
	this.healthBackground = QuickLoad("HealthBackground");
	this.healthForeground = QuickLoad("HealthForeground");
	this.healthBorder = QuickLoad("HealthBorder");
	this.startTile = startTile;
	this.x = startTile.getX();
	this.y = startTile.getY();
	this.width = width;
	this.height = height;
	this.speed = speed;
	this.health = health;
	this.startHealth = health;
	this.hiddenHealth = health;
	this.angle = 0;
	this.grid = grid;
	this.first = true;
	this.alive = true;
	this.frosted = false;
	this.checkpoints = new ArrayList<Checkpoint>();
	this.bounty = bounty;
	
	this.directions = new int[2];
	//initialize X direction
	this.directions[0] = 0;
	//initialize Y direction
	this.directions[1] = 0;
	directions = findNextD(startTile);
	
	//starting with  0 checkpoint
	this.currentCheckpoint = 0;
	//fill the entire list of checkpoints at start
	populateCheckpointList();
	}
	
	//Update moves enemy in single direction
	public void update() {
		// ignore initial first(true)
		if (first) {
			first = false;
			calculateAngle(startTile, checkpoints.get(currentCheckpoint).getTile());
		} else {
			if(checkpointReached()){	//check if there are more checkpoints before moving on 
				
				if(currentCheckpoint + 1 == checkpoints.size()){
					endOfMazeReached();	//Enemy reached end of the maze, take one player live and remove enemy form updating
				} else {
					
					//if we reached a checkpoint go on to a new direction
					currentCheckpoint++;
				}
				calculateAngle(checkpoints.get(currentCheckpoint - 1).getTile(), checkpoints.get(currentCheckpoint).getTile());
			} else {
				
				//if not a checkpoint, continue in current direction, no rotation
				x += Delta() * checkpoints.get(currentCheckpoint).getxDirection() * speed;
				y += Delta() * checkpoints.get(currentCheckpoint).getyDirection() * speed;
			}
		}
	}
	
	/*
	 * Calculates distances between two neighboring checkpoints to find on witch of the two axes
	 * is current movement and what that movement direction is 
	 */
	public void calculateAngle(Tile from, Tile to){
		
		// resultX = from.getXPlace() - to.getXPlace();
		// resultY = from.getYPlace() - to.getYPlace();
		
		if(from.getXPlace() - to.getXPlace() < 0 && from.getYPlace() - to.getYPlace() == 0){
			//enemy is moving right
			this.setAngle(360);
		}
		
		if(from.getXPlace() - to.getXPlace() > 0 && from.getYPlace() - to.getYPlace() == 0){
			//enemy is moving left
			this.setAngle(180);
		}
		
		if(from.getXPlace() - to.getXPlace() == 0 && from.getYPlace() - to.getYPlace() < 0){
			//enemy is moving down
			this.setAngle(90);
		}
		
		if(from.getXPlace() - to.getXPlace() == 0 && from.getYPlace() - to.getYPlace() > 0){
			//enemy is moving up
			this.setAngle(270);
		}
	}
	
	//run when last checkpoint is reached by enemy
	private void endOfMazeReached(){
		Player.modifyLives(-1);//player loses live
		die();//creep dies
	}
	
	private boolean checkpointReached(){
		
		boolean reached = false;
		//get current checkpoint tile
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		/* Check 3 tiles in advance, we need leeway for the proper Update() 
		if enemy move two fast, without that leeway, the direction changes will
		came to realization after they were needed	*/
		if(	x > t.getX() - 3 && 
			x < t.getX() + 3 &&
			y > t.getY() - 3 &&
			y < t.getY() + 3){
			
				reached = true;
				x = t.getX();	//float tile x coordinate
				y = t.getY();	//float tile y coordinate
		}
		return reached;
	}

	
	/**
	 * fill CheckpointList array
	 */
	private void populateCheckpointList(){
		//Ad first checkpoint manually based on startTile
		//now directions[] = FindNextD(startTile)
		checkpoints.add(findNextC(startTile, directions = findNextD(startTile)));
		
		//add. findNextCheckpoint( StartTile, FindNextDirection(startTile) )
		int counter = 0;
		boolean cont = true;
	
		//after first one loop for the others
		while(cont){		//as long as we continue to search
			//first find next direction
			int[] currentD = findNextD(checkpoints.get(counter).getTile());
			//direction can == 1 or 2, if == 3 then there is no direction found, so stop searching
			if(currentD[0] == 2 || counter == 20){
				cont = false;
			} else {
				checkpoints.add(findNextC(checkpoints.get(counter).getTile(), 
						directions = findNextD(checkpoints.get(counter).getTile()) ) );
			}
			counter++;
		}
	}
	
	//Find next Checkpoint method
	private Checkpoint findNextC(Tile s, int[] dir){
		
		Tile next = null;
		Checkpoint c = null; 
		
		//boolean to decide if next checkpoint is found
		boolean found = false;
		
		//Integer to increment each loop
		int counter = 1;
		
		while(!found){
			
			/* from the current tile "s" we're on
			 * get x and y coordinate with their corresponding directions
			 * NOTE: if there is no y direction dir[1]*counter == 0 
			 * go in that directions counter amount of times
			 * ONLY if it is the same as the first tile "s" */	
			
			if(s.getXPlace() + dir[0] * counter == grid.getTilesWide() ||
				s.getYPlace() + dir[1] * counter == grid.getTilesHigh() ||
					s.getType() != grid.getTile(s.getXPlace() + dir[0] * counter,
						s.getYPlace() + dir[1] * counter).getType()	){
				
				found = true;
				counter -= 1;
				next = grid.getTile(
					s.getXPlace() + dir[0] * counter,
					s.getYPlace() + dir[1] * counter);
			}
		counter++;
		}
		//  THEN go one previous AND return checkpoint
		// "c" with tile next
		c = new Checkpoint(next, dir[0], dir[1]);
		
		return c;
	}

	
	/**
	 *Calculate what direction to go next
	 *@param s pass the current tile that enemy is on
	 *@return directions to directions[]
	 */
	private int[] findNextD(Tile s) {
		int[] dir = new int[2];
		Tile u = grid.getTile(s.getXPlace(), s.getYPlace() - 1);
		Tile r = grid.getTile(s.getXPlace() + 1, s.getYPlace());
		Tile d = grid.getTile(s.getXPlace(), s.getYPlace() + 1);
		Tile l = grid.getTile(s.getXPlace() - 1, s.getYPlace());
		
		if (s.getType() == u.getType() && directions[1] != 1 ) {	//first check if current inhabited tile == upper tile AND direction is not DOWN
		        dir[0] = 0;
		        dir[1] = -1;
		        
		} else if (s.getType() == r.getType() && directions[0] != -1 ) {	//then check if current inhabited tile  == right tile AND direction is not LEFT
		        dir[0] = 1;
		        dir[1] = 0;
		        
		} else if (s.getType() == d.getType() && directions[1] != -1 ) {	//then check if current inhabited tile  == down tile AND direction is not UP
		        dir[0] = 0;
		        dir[1] = 1;
		        
		} else if (s.getType() == l.getType() && directions[0] != 1 ) {	//last check if current inhabited tile  == left tile tile AND direction is not RIGHT
		        dir[0] = -1;
		        dir[1] = 0;
		        
		} else {	//there is no direction at all
				dir[0] = 2;
		        dir[1] = 2;
		}
		return dir;
	}
	
	//Take damage from external source
	public void damage(int amount){
		health -= amount;
		if(health <= 0){
			die();
			Player.killCount++;
			Player.modifyCash(bounty);//add reward
		}
	}
	
	private void die(){
		alive = false;
	}
	
	public void reduceHiddenHealth(float amount){
		hiddenHealth -= amount;
	}
	
	public float getHiddenHealth(){
		return hiddenHealth;
	}
	
	public void setHiddenHealth(int hiddenHealth) {
		this.hiddenHealth = hiddenHealth;
	}
	
	/**
	 * draw texture of enemy with 
	 * texture,  X coordinate, Y coordinate, width and height
	 */
	public void draw() {
		
		//draw enemy
		DrawQuadTexRot(texture, x, y, width, height, angle);
		
		//health bar textures
		float healthPercentage = health / startHealth;
		DrawQuadTex(healthBackground, x, y - 8, width, 8);
		DrawQuadTex(healthForeground, x, y - 8, TILE_SIZE * healthPercentage, 8);
		DrawQuadTex(healthBorder, x, y - 8, width, 8);
	}

	// GETTERS AND SETTERS
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void setTexture(String textureName) {
		this.texture = QuickLoad(textureName);
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public TileGrid getTileGrid() {
		return grid;
	}

	public boolean isAlive() {
		return alive;
	}
	
	public void setAngle(float angle){
		this.angle = angle - 90;
	}
	
	public boolean isFrosted() {
		return frosted;
	}
	
	public void setFrosted(boolean frosted){
		this.frosted = frosted;
	}


	public int getBounty() {
		return bounty;
	}

	public void setBounty(int bounty) {
		this.bounty = bounty;
	}
}
