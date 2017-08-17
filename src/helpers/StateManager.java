package helpers;

import data.MainMenu;
import static data.GameScenario.*;
import data.Editor;


public class StateManager {

	public static enum GameState{
		
		MAINMENU, GAME, LEVEL2, LEVEL3, LEVEL4, LEVEL5, LEVEL6, LEVEL7, LEVEL8, VICTORY, EDITOR		//there is no need for GameState.QUIT just: System.exit(0);
	}
	//start with value MAINMENU
	public static GameState gameState = GameState.MAINMENU;
	public static MainMenu mainMenu;
	public static Editor editor;
	
	public static long nextSecond = System.currentTimeMillis() + 1000;
	public static int framesInLastSecond = 0;	//what we are tracking
	public static int framesInCurrentSecond = 0;//what we are tracking
	
	public static void update(){
		
		switch(gameState){
		case MAINMENU:
			if(mainMenu == null){
				mainMenu = new MainMenu();
			}
			mainMenu.update();
			break;
			
		case GAME:		Level_1();		break;
			
		case LEVEL2:	Level_2();		break;
			
		case LEVEL3:	Level_3();		break;
			
		case LEVEL4:	Level_4();		break;	
			
		case LEVEL5:	Level_5();		break;

		case LEVEL6:	Level_6();		break;
		
		case LEVEL7:	Level_7();		break;
			
		case LEVEL8:	Level_8();		break;
		
		case VICTORY:	Victory();		break;
		
		case EDITOR:
			if(editor == null){
				editor = new Editor();
			}
			editor.update();
			break;
		}
		
		
		/**
		 *For diagnostics FPS counter
		 */
		long currentTime = System.currentTimeMillis();
		if(currentTime > nextSecond){
			//in the span of 1 second, how may times we are going to increase framesInCurrentSecond
			nextSecond += 1000;
			framesInLastSecond = framesInCurrentSecond;
			framesInCurrentSecond = 0;
			//System.out.println(framesInLastSecond + " fps");
		}
		framesInCurrentSecond++;
	}
	
	public static void setState(GameState newState){
		gameState = newState;
	}
}
