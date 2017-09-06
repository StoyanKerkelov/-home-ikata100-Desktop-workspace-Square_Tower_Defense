package data;

import static helpers.Artist.HEIGHT;
import static helpers.Artist.TILE_SIZE;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Clock;

/**
 * Implements player class
 */
public class Player {

	static int killCount;
	private TileGrid grid;
	private TileType[] types;	//saves tile types
	private WaveManager waveManager;
	private ArrayList<Tower> towerList;
	private boolean leftMouseButtonDown, rightMouseButtonDown, holdingTower;
	private Tower tempTower;
	public static int Credits;
	public static int Lives;
	
	//check if player can afford a tower, is so build
	public static boolean modifyCash(int amount){
		if(Credits + amount >= 0){
			Credits += amount;
//			System.out.println(Credits);
			return true;
		}
//		System.out.println(Credits);
		return false;
	}
	
	public Player(TileGrid grid, WaveManager waveManager){
		this.grid = grid;
		this.types = new TileType[5];	//	Set number of TileTypes for player
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.types[3] = TileType.Rock;
		this.types[4] = TileType.Forest;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<Tower>();
		this.leftMouseButtonDown = false;
		this.rightMouseButtonDown = false;
		this.holdingTower = false;
		this.tempTower = null;
	}
	
	//Initialize credits and Lives values for Player
	//for campaign (with saved player progress) this is not necessary
	public void setup(){
		Credits = 100;
		Lives = 10;
	}
	

	public static void modifyLives(int amount){
		Lives += amount;
	}
	
	public void update(){
		
		//Update holdingTower
		if(holdingTower){
			tempTower.setX(getMouseTile().getX());
			tempTower.setY(getMouseTile().getY());
			tempTower.draw();
		}
		
		//graphically update all towers in game
		for(Tower t : towerList){ 
			t.update();
			t.draw();
			//every time we update towers we also update enemy list
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());	//
		}
		
		//Handle mouse input Mouse.isButtonDown(0) - left click, 
		if(Mouse.isButtonDown(0) && !leftMouseButtonDown){
			placeTower();
		}
		leftMouseButtonDown = Mouse.isButtonDown(0);	//track left button click

		//right click
		if(Mouse.isButtonDown(1) && !rightMouseButtonDown){
		
		}
		rightMouseButtonDown = Mouse.isButtonDown(1);	//track right button click
		
		//Handle keyboard input
		while (Keyboard.next()){
			//Keyboard.getEventKeyState() - read button one time per press
			if(Keyboard.getEventKey() == Keyboard.KEY_ADD && Keyboard.getEventKeyState()){
				Clock.ChangeMultiplier(0.2f);
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_SUBTRACT && Keyboard.getEventKeyState()){
				Clock.ChangeMultiplier(-0.2f);
			}
		}
	}
	
	private void placeTower(){
		Tile currentTile = getMouseTile();
		if(holdingTower){
			//first check if tile is occupied and only if not charge for tower
			if(!currentTile.getOccupied() && modifyCash(-tempTower.getCost())){
				towerList.add(tempTower);
				currentTile.setOccupied(true);
				holdingTower = false;
				tempTower = null;
			}
		}
	}
	
	public void pickTower(Tower t){
		tempTower = t;
		holdingTower = true;
	}
	
	private Tile getMouseTile(){
		return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
	}
	
	public void setGrid(TileGrid grid) {
		this.grid = grid;
	}

	public void setWaveManager(WaveManager waveManager) {
		this.waveManager = waveManager;
	}
	
	public int getLives() {
		return Lives;
	}

	public void setLives(int lives) {
		Lives = lives;
	}

	public int getCredits() {
		return Credits;
	}
	
	public void setCredits(int credits) {
		Credits = credits;
	}
	
	public int getKillCount() {
		return killCount;
	}
	
	public void setKillCount(int killcount) {
		killCount = killcount;
	}
}
