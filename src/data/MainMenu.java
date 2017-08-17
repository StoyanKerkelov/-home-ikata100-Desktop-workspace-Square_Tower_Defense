package data;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;
import helpers.StateManager;
import helpers.StateManager.GameState;

import static helpers.Artist.*;

public class MainMenu {

	private Texture background;
	private UI menuUI;
	
	public MainMenu(){
		
		background = QuickLoad("Mainmenu");
		menuUI = new UI();
		menuUI.addButton("Play", "CampaignButton", WIDTH / 2 - 128,(int) (HEIGHT * 0.45f));
		menuUI.addButton("Editor", "EditorButton", WIDTH / 2 - 128,(int) (HEIGHT * 0.55f));
		menuUI.addButton("Quit", "QuitButton", WIDTH / 2 - 128,(int) (HEIGHT * 0.65f));
	}
	
	private void updateButtons(){
		
		//Check if the button is clicked by the user, and if so do an action
		if(Mouse.isButtonDown(0)){
			if(menuUI.isButtonClicked("Play")){
				menuUI.drawLoadingString(630, 860,"LOADING");
				StateManager.setState(GameState.GAME);
			}
			if(menuUI.isButtonClicked("Editor")){
				StateManager.setState(GameState.EDITOR);
			}
			if(menuUI.isButtonClicked("Quit")){
				System.exit(0);
			}
		}
	}
	
	public void update(){
		DrawQuadTex(background, 0, 0, 2048, 1024);
		menuUI.draw();
		updateButtons();
	}
}
