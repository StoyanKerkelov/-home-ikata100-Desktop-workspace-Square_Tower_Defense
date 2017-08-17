package data;

public class WaveManager {

	private float  timeBetweenEnemies;
	private boolean levelFinished;
	private int waveNumber, wavesForTheLevel, currentEnemy;
	private int[] enemiesPerWave;
	private Enemy[] enemyTypes;
	private Wave currentWave;
	
	
	public WaveManager(Enemy[] enemyTypes, float timeBetweenEnemies, int[] enemiesPerWave, int wavesForTheLevel){
		
		this.levelFinished = false;
		this.enemyTypes = enemyTypes;
		this.timeBetweenEnemies = timeBetweenEnemies;
		this.wavesForTheLevel = wavesForTheLevel;
		this.enemiesPerWave = enemiesPerWave;
		this.currentEnemy = 0;
		this.waveNumber = 0;	//counting every wave of the waveManager up to wavesForTheLevel
		this.currentWave = null;
		//	newRandomWave();
		newWave();
		
	}
	
	public void update(){
		//if the wave is not killed, continue to update it
		if(!currentWave.isCompleted()){
			currentWave.update();
		} else {
			if(waveNumber >= wavesForTheLevel){
				levelFinished = true;
			} else {
			//	newRandomWave();
				newWave();
			}
		}
	}
	
	private void newWave(){
		currentWave = new Wave(enemyTypes, currentEnemy, timeBetweenEnemies, enemiesPerWave[waveNumber]);
		waveNumber++;
		currentEnemy++;
	}
	
	//creates waves of enemies with random enemy type
	private void newRandomWave(){
		currentWave = new Wave(enemyTypes, timeBetweenEnemies, enemiesPerWave[waveNumber]);
		waveNumber++;
	//	System.out.println("Beginning Wave " + waveNumber);
	}
	
	
	public Wave getCurrentWave(){
		return currentWave;
	}
	
	public int getWaveNumber(){
		return waveNumber;
	}

	public boolean isLevelFinished() {
		return levelFinished;
	}
	
	public void setLevelFinished(boolean set) {
		levelFinished = set;
	}
}