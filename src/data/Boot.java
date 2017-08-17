package data;

import helpers.Clock;
import helpers.StateManager;
import org.lwjgl.opengl.Display;
import static helpers.Artist.*;

public class Boot {
	public static void main(String[] args) {
		new Boot();
	}
	
	public Boot(){
		
		//Calls static method in Artist class to initialize OpenGL call
		BeginSession();
		
		//game loop
		while(!Display.isCloseRequested()){
			Clock.update();		// update clock 
			StateManager.update();
			Display.update();
			Display.sync(60);	//fps
		}
		Display.destroy();
	}
}
