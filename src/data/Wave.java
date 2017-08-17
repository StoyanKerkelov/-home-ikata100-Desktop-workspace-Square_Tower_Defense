package data;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static helpers.Clock.*;
import static helpers.Artist.TILE_SIZE;

/**
 * Group of enemies in wave
 */
public class Wave {
	
	/*We did not get any ConcurrentModificationExceptionat as 
	 *CopyOnWriteArrayList keeps a copy of original List and 
	 *iterate over this. The new value which has been
	 *added/removed is merged to copy of original array only
	 *after Iteration is over
	 */
	
	
	private float timeSinceLastSpawn, spawnTime;
	private int enemiesPerWave, enemiesSpawned, enemyForSpawing;
	private boolean waveCompleted;
	
	private Enemy[] enemyTypes;
	private CopyOnWriteArrayList<Enemy> enemyList;
	
	//controlled wave
	public Wave(Enemy[] enemyTypes, int currentEnemy, float spawnTime, int enemiesPerWave){
		
		this.enemyTypes = enemyTypes;
		this.enemyForSpawing = currentEnemy;
		this.spawnTime = spawnTime;
		this.enemiesPerWave = enemiesPerWave;
		this.enemiesSpawned = 0;
		this.timeSinceLastSpawn = 0;
		this.enemyList = new CopyOnWriteArrayList<Enemy>();
		this.waveCompleted = false;
		
		spawn(enemyForSpawing);//first spawn does not wait for spawnTime
	}
	
	
	//Random wave
	public Wave(Enemy[] enemyTypes, float spawnTime, int enemiesPerWave){
		
		this.enemyTypes = enemyTypes;
		this.spawnTime = spawnTime;
		this.enemiesPerWave = enemiesPerWave;
		this.enemiesSpawned = 0;
		this.timeSinceLastSpawn = 0;
		this.enemyList = new CopyOnWriteArrayList<Enemy>();
		this.waveCompleted = false;
		
		spawnRandom();//first spawn does not wait for spawnTime
	}
	
	
	public void update(){
		//Assume all enemies are dead, until full prove
		boolean allEnemiesDead = true;
		
		//Spawn() enemy until the whole wave is printed
		if(enemiesSpawned < enemiesPerWave){
			//increase time since last spawn with delta * multiplier time
			timeSinceLastSpawn += Delta();
			
			if(timeSinceLastSpawn > spawnTime){
				
				//spawnRandom();
				spawn(enemyForSpawing);
				
				timeSinceLastSpawn = 0;
			}
		}
		for( Enemy e : enemyList){
			//draw enemy if it is alive
			if(e.isAlive() ){
				//if just one enemy is alive, then 
				allEnemiesDead = false;
				e.update();
				e.draw();
			} else {//remove dead from list
				enemyList.remove(e);
			}
		}
		if(allEnemiesDead){
			waveCompleted = true;
		}
	}

	//Add the new enemy to enemyList 
	private void spawn(int enemyForSpawing) {
		
		enemyList.add(new Enemy( enemyTypes[enemyForSpawing].getTexture(), 
			enemyTypes[enemyForSpawing].getStartTile(), enemyTypes[0].getTileGrid(),
			TILE_SIZE, TILE_SIZE, enemyTypes[enemyForSpawing].getSpeed(), enemyTypes[enemyForSpawing].getHealth(), enemyTypes[enemyForSpawing].getBounty()));
		enemiesSpawned++;
	}
	
	//Add the new random type enemy to enemyList 
	private void spawnRandom() {
		
		int enemyChosen = 0;
		Random random = new Random();
		enemyChosen = random.nextInt(enemyTypes.length);
		
		enemyList.add(new Enemy( enemyTypes[enemyChosen].getTexture(), 
			enemyTypes[enemyChosen].getStartTile(), enemyTypes[0].getTileGrid(),
			TILE_SIZE, TILE_SIZE, enemyTypes[enemyChosen].getSpeed(), enemyTypes[enemyChosen].getHealth(), enemyTypes[enemyForSpawing].getBounty() ));
		enemiesSpawned++;
	}
	
	public boolean isCompleted(){
		return waveCompleted;
	}
	
	public CopyOnWriteArrayList<Enemy> getEnemyList(){
		return enemyList;
	}
}
