package data;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.HEIGHT;
import static helpers.Artist.QuickLoad;
import static helpers.Artist.TILE_SIZE;
import static helpers.Leveler.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;
import UI.UI.Menu;
import helpers.StateManager;
import helpers.StateManager.GameState;
 
public class Editor{

	private TileGrid grid;
	private int index;
	private TileType[] types;
	private UI editorUI;
	private Menu tilePickerMenu;
	private Texture menuBackground;
	private String loadedMap = "newMapTemplate";
	private String savedMap = "customMap";
	
	
	public Editor(){
		grid = LoadMap(loadedMap);				//new TileGrid();
		this.index = 0;
		
		this.types = new TileType[5];			//Set TileTypes for Editor
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.types[3] = TileType.Rock;
		this.types[4] = TileType.Forest;
		
		this.menuBackground = QuickLoad("MenuEditor");
		setupUI();
	}
	
	private void setupUI(){
		editorUI = new UI();
		editorUI.createMenu("TilePicker", 1280, 100, 192, 960, 2, 0);
		tilePickerMenu = editorUI.getMenu("TilePicker");
		tilePickerMenu.quickAdd("GrassButton", "TileGrass");
		tilePickerMenu.quickAdd("DirtButton", "TileDirt");
		tilePickerMenu.quickAdd("WaterButton", "TileWater");
		tilePickerMenu.quickAdd("RockButton", "TileRock");
		tilePickerMenu.quickAdd("ForestButton", "TileForest");
		
		//add menu buttons
		editorUI.addButton("SaveMap", "SaveMapButton", 1320, 450);
		editorUI.addButton("ReturnToMainMenu", "ReturnToMainMenuButton", 1320, 520);
		
	}
	
	
	public void update(){
		draw();
		
		//Handle mouse input Mouse.isButtonDown(0) - left click, 
		if(Mouse.next()){
			boolean mouseClicked = Mouse.isButtonDown(0);
			if(mouseClicked){
				// non editor button actions
				if(editorUI.isButtonClicked("SaveMap")){
					SaveMap(savedMap, grid); 
				}
				if(editorUI.isButtonClicked("ReturnToMainMenu")){
					StateManager.setState(GameState.MAINMENU);
				}
				
				//	editor button actions
				if(	!(editorUI.isButtonClicked("SaveMap")) && !(editorUI.isButtonClicked("ReturnToMainMenu")) ){
					
					if(tilePickerMenu.isButtonClicked("GrassButton")){
						index = 0;
					} else if (tilePickerMenu.isButtonClicked("DirtButton")){
						index = 1;
					} else if(tilePickerMenu.isButtonClicked("WaterButton")){
						index = 2;
					} else if(tilePickerMenu.isButtonClicked("RockButton")){
						index = 3;
					} else if(tilePickerMenu.isButtonClicked("ForestButton")){
						index = 4;
					} else {
						setTile();
					}
				}
			}
		}
	}
	
	private void draw(){
		DrawQuadTex(menuBackground, 1280, 0, 192, 960);
		grid.draw();
		editorUI.draw();
	}
	
	// Like the TileGrid setTile() method, but instead it works in real time.
	private void setTile(){
		
		//Tracking mouse cursor
		grid.setTile((int) Math.floor(Mouse.getX() / TILE_SIZE),
			(int) Math.floor( (HEIGHT - Mouse.getY() - 1 ) / TILE_SIZE),
			types[index]);
	}
}
