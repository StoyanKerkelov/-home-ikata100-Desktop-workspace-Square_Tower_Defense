package UI;

import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.Color;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import static helpers.Artist.*;

public class UI {

	private ArrayList<Button> buttonList;
	private ArrayList<Menu> menuList;
	private Font awtFont;
	private Font awtTitleFont;
	private Font awtPauseFont;
	private Font awtLoadingPromptFont;
	private TrueTypeFont font;
	private TrueTypeFont Tfont;
	private TrueTypeFont Pfont;
	private TrueTypeFont Lfont;
	
	
	public UI(){
		buttonList = new ArrayList<Button>();
		menuList = new ArrayList<Menu>();
		//status menu
		awtFont = new Font("Terbuchet", Font.BOLD, 20);//or	Century Gothic
		font = new TrueTypeFont(awtFont, false);//false for anti-aliasing
		//titles at begging new level
		awtTitleFont = new Font("Comic Sans MS", Font.ITALIC, 45);
		Tfont = new TrueTypeFont(awtTitleFont, false);
		//pause
		awtPauseFont = new Font("Courier New", Font.BOLD, 52);
		Pfont = new TrueTypeFont(awtPauseFont, false);
		//loading
		awtLoadingPromptFont = new Font("Courier New", Font.BOLD, 52);
		Lfont = new TrueTypeFont(awtLoadingPromptFont, false);
		
		
	}
	
	public void drawString(int x, int y, String text){
		font.drawString(x, y, text);
	}
	
	public void drawTitleString(int x, int y, String text){
		Tfont.drawString(x, y, text);
	}
	
	public void drawPauseString(int x, int y, String text){
		Pfont.drawString(x, y, text, Color.gray);
	}
	
	public void drawLoadingString(int x, int y, String text){
		Lfont.drawString(x, y, text);
	}
	
	//Adding button without menu, hard-coding place and size
	public void addButton(String name, String textureName, int x, int y){
		
		buttonList.add(new Button(name, QuickLoad(textureName), x, y));
	}
	
	//Check if button is clicked
	public boolean isButtonClicked(String buttonName){
	
		Button b = getButton(buttonName);
		float mouseY = HEIGHT - Mouse.getY() - 1;
		// check if mouse is in top of button and if is not blocked
		if(b.getAvailability() && 
				Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() &&
				mouseY > b.getY() && mouseY < b.getY() + b.getHeight()){
			return true;
		}
		return false;
	}
	
	public void killButton(String buttonName){
		for(Button b: buttonList){
			if(b.getName().equals(buttonName)){
				b.setAvailability(false);
			}
		}
	}
	
	public void bringButtonBack(String buttonName){
		for(Button b: buttonList){
			if(b.getName().equals(buttonName)){
				b.setAvailability(true);
			}
		}
	}
	
	//get button by String buttonName
	public Button getButton(String buttonName){
		for(Button b: buttonList){
			if(b.getName().equals(buttonName)){
				return b;
			}
		}
		return null;
	}
	
	public void createMenu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight){
		menuList.add(new Menu(name, x, y, width, height, optionsWidth, optionsHeight));
	}
	
	public Menu getMenu(String name){
		for(Menu m : menuList){
			if(name.equals(m.getName())){
				return m;
			}
		}
		return null;
	}
	
	//draw buttons
	public void draw(){
		
		for(Button b : buttonList){
			DrawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		for(Menu m : menuList){
			m.draw();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public class Menu{
		
		//implementing In-game Menu
		
		String name;
		private ArrayList<Button> menuButtons;
		private int x, y, buttonAmount, optionsWidth, padding;
	
		public Menu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight){
			
			this.name = name;
			this.x = x;
			this.y = y;
			this.optionsWidth = optionsWidth;
			//				---[button]---[button]---[button]---
			this.padding = (width - (optionsWidth * TILE_SIZE)) / (optionsWidth + 1);
			this.buttonAmount = 0;
			this.menuButtons = new ArrayList<Button>();
		}
		
		//every time we add button, the next button will be one further along
		public void addButton(Button b){
			setButton(b);
		}
		
		//add button IN MENU, then the placement is ordered by the set menu instance
		public void quickAdd(String buttonName, String buttonTextureName){
			Button b = new Button(buttonName, QuickLoad(buttonTextureName), 0, 0);
			setButton(b);
		}
		
		//set button with auto placing
		private void setButton(Button b){
			
			int row;
			if((buttonAmount + 1) % optionsWidth == 0){
				row = (buttonAmount + 1) / optionsWidth - 1;
			} else {
				row = (buttonAmount + 1) / optionsWidth;
			}
			
			if(optionsWidth != 0){
				b.setY(y + row*padding +(buttonAmount / optionsWidth) * TILE_SIZE);
			}
			b.setX(x + (buttonAmount % 2)* (padding + TILE_SIZE) + padding);
			buttonAmount++;
			menuButtons.add(b);
		}
		
		public boolean isButtonClicked(String buttonName){
		
			Button b = getButton(buttonName);
			float mouseY = HEIGHT - Mouse.getY() - 1;
			
			// check is mouse is in top of button
			if(Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() &&
					mouseY > b.getY() && mouseY < b.getY() + b.getHeight()){
				return true;
			}
			return false;
		}
		
		public Button getButton(String buttonName){
			for(Button b: menuButtons){
				if(b.getName().equals(buttonName)){
					return b;
				}
			}
			return null;
		}
		
		public void draw(){
			
			for(Button b : menuButtons){
				DrawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}
		}

		public String getName(){
			return name;
		}
	}
}
