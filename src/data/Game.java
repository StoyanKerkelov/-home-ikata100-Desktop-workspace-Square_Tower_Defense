package data;

import static helpers.Artist.QuickLoad;
import static helpers.Artist.DrawQuadTex;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.Button;
import UI.UI;
import UI.UI.Menu;
import helpers.Clock;
import helpers.StateManager;
import helpers.StateManager.GameState;
public class Game {

	private String levelName;
	private TileGrid grid;
	private Player gamer;
	private WaveManager waveManager;
	private UI gameUI;
	private Menu towerPickerMenu;
	private Texture menuBackground;
	private Texture playButton = QuickLoad("PlayButton");
	private Texture pauseButton = QuickLoad("PauseButton");
	private boolean pausedGame = false;
	private boolean showFPS = false;
	private static boolean victoryReached = false;
	private static int Level = 1, Credits = 100, PreviousLevelLives = 10, PreviousLevelKills = 0;
	private static long sessionBeginning;
	public long currentTime;
	
	
	public Game(TileGrid grid, String levelName, WaveManager waveManager){
		this.grid = grid;
		this.levelName = levelName;
		this.waveManager = waveManager;
		this.gamer = new Player(grid, waveManager);
		this.gamer.setCredits(Credits);
		this.gamer.setLives(PreviousLevelLives);
		this.gamer.setKillCount(PreviousLevelKills);
		if(!victoryReached){
			this.menuBackground = QuickLoad("MenuGameMenu");
		} else {
			this.menuBackground = QuickLoad("EmptyMainMenu");
		}
		sessionBeginning = System.currentTimeMillis() / 1000;
		setupUI();
	}
	
	
	private void nextLevel(){
		
		switch(Level){
		
		case 2:
			StateManager.setState(GameState.LEVEL2);
			break;
		case 3:
			StateManager.setState(GameState.LEVEL3);
			break;
		case 4:
			StateManager.setState(GameState.LEVEL4);
			break;
		case 5:
			StateManager.setState(GameState.LEVEL5);
			break;
		case 6:
			StateManager.setState(GameState.LEVEL6);
			break;
		case 7:
			StateManager.setState(GameState.LEVEL7);
			break;
		
		case 8:
			StateManager.setState(GameState.LEVEL8);
			break;
		
		case 9:
			victoryReached = true;
			StateManager.setState(GameState.VICTORY);
			break;
		}
	}
	
	public void setupUI(){
		gameUI = new UI();
		
		if(!victoryReached){
		
			//createMenu("name", x, y, optionsWidth, optionsHeight)
			gameUI.createMenu("TowerPicker", 1280, 100, 192, 960, 2, 0);
			towerPickerMenu = gameUI.getMenu("TowerPicker");
			
			//add buttons, they place automatically in order
			towerPickerMenu.quickAdd("GunTowerButton", "GunTowerIcon");
			towerPickerMenu.quickAdd("FireTowerButton", "FireTowerIcon");
			towerPickerMenu.quickAdd("IceTowerButton", "IceTowerIcon");
			towerPickerMenu.quickAdd("PoisonTowerButton", "PoisonTowerIcon");
			towerPickerMenu.quickAdd("RocketTowerButton", "RocketTowerIcon");
			
			//add menu buttons	
			gameUI.addButton("PauseButton", "PauseButton", 1320,450);
		}
		gameUI.addButton("ReturnToMainMenu", "ReturnToMainMenuButton", 1320,520);
	}
	
	//Draw status info
	private void updateUI(){
		
		
		gameUI.draw();
		
		if(!victoryReached){
			gameUI.drawString(1320, 638, "Wave:       " + waveManager.getWaveNumber());
			gameUI.drawString(1320, 662, "Lives:      " + Player.Lives);
			gameUI.drawString(1320, 688, "Credits:   " + Player.Credits);
			gameUI.drawString(1320, 713, "Kills:        " + Player.killCount);
			gameUI.drawString(1320, 738, "Time:        " + (currentTime - sessionBeginning) );
		}
		
		if(currentTime - sessionBeginning < 3){
			gameUI.drawTitleString(50, 440, levelName );
		}
		
		//pause game massage
		if(pausedGame){
			gameUI.drawPauseString(425, 260, "Game is Paused" );
		}
		
		//game over massage
		if(Player.Lives < 1){
			gameUI.drawTitleString(540, 440, "Game over" );
			//stop game
			gameUI.killButton("PauseButton");
			Clock.GameOver();
		}
	
		if(Keyboard.getEventKey() == Keyboard.KEY_F && Keyboard.getEventKeyState()){
			showFPS = !showFPS;
		}
		
		if(showFPS){
			gameUI.drawString(0, 0, StateManager.framesInLastSecond + " fps");
		}
		
		if(Mouse.next()){
			boolean mouseClicked = Mouse.isButtonDown(0);
			if(mouseClicked){
				
				//build selected tower
				if(!victoryReached && towerPickerMenu.isButtonClicked("FireTowerButton")){
					gamer.pickTower(new TowerCFire(TowerType.TowerFire, grid.getTile(0, 0),
							waveManager.getCurrentWave().getEnemyList()));
				}
				if(!victoryReached && towerPickerMenu.isButtonClicked("IceTowerButton")){
					gamer.pickTower(new TowerCIce(TowerType.TowerIce, grid.getTile(0, 0),
							waveManager.getCurrentWave().getEnemyList()));
				}
				if(!victoryReached && towerPickerMenu.isButtonClicked("PoisonTowerButton")){
					gamer.pickTower(new TowerCPoison(TowerType.TowerPoison, grid.getTile(0, 0),
							waveManager.getCurrentWave().getEnemyList()));
				}
				if(!victoryReached && towerPickerMenu.isButtonClicked("GunTowerButton")){
					gamer.pickTower(new TowerCGun(TowerType.TowerGun, grid.getTile(0, 0),
							waveManager.getCurrentWave().getEnemyList()));
				}
				if(!victoryReached && towerPickerMenu.isButtonClicked("RocketTowerButton")){
					gamer.pickTower(new TowerCRocket(TowerType.TowerRocket, grid.getTile(0, 0),
							waveManager.getCurrentWave().getEnemyList()));
				}
			
				// main menu button actions
				if(!victoryReached && gameUI.isButtonClicked("PauseButton")){
					//pause game
					Clock.GamePause();
					pausedGame = !pausedGame;
					
					if(pausedGame){
						gameUI.killButton("ReturnToMainMenu");
					}else{
						gameUI.bringButtonBack("ReturnToMainMenu");
					}
					
					//switch icon pause / play
					Button b = gameUI.getButton("PauseButton");
					if(b.getTexture().equals(playButton)){
						b.setTexture(pauseButton);
					} else {
						b.setTexture(playButton);
					}
				}
				
				if(gameUI.isButtonClicked("ReturnToMainMenu")){
					terminateProgess();
					StateManager.setState(GameState.MAINMENU);
				}
			}
		}
		
		currentTime = System.currentTimeMillis() / 1000;
	}
	
	
	//Reset static variables for next new session
	private void terminateProgess(){
		
		if(Clock.isPaused()){
			Clock.setPaused(false);
		}
		setLevel(1);
		Credits = 100;
		PreviousLevelLives = 10;
		PreviousLevelKills = 0;
		victoryReached = false;
		
		for(int i = 0; i < GameScenario.levels.length; i++){
			GameScenario.levels[i] = null;
		}
	}
	
	public void update(){
		//order is important! prints stays at the back, last stays at the front
		if(waveManager == null){
			waveManager.setLevelFinished(false);
		}
		if(waveManager.isLevelFinished()){
		Level++;
		Credits = 100;
		PreviousLevelLives = this.gamer.getLives();
		PreviousLevelKills = this.gamer.getKillCount();
		nextLevel();
		}
		DrawQuadTex(menuBackground, 1280, 0, 192, 960);
		grid.draw();
		waveManager.update();
		gamer.update();
		updateUI();
	}
	
	public static void setLevel(int level) {
		Level = level;
	}
}
