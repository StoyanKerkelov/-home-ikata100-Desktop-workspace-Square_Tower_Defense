package helpers;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.io.InputStream;


import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display; 
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Artist {
	//res 1280(+ 192x for menu) x 960 
	public static final int WIDTH = 1472, HEIGHT = 960;
	public static final int TILE_SIZE = 64;

	public static void BeginSession() {
		Display.setTitle("Square Tower Defense");
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		
		//Blend the enemy image with the background so no whitespace is shown
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
	}
	
	//basic collision check
	public static boolean CheckCollision(float x1, float y1, float width1, float height1, 
			float x2, float y2, float width2, float height2){
		
		if(	x2 < x1 + width1 && x1 < x2 + width2 &&
			y2 < y1 + height1 && y1 < y2 + height2 ){
			return true;
		}
		return false;
	}
	
	//Draw Quad
	public static void DrawQuad(float x, float y, float width, float height){
		glBegin(GL_QUADS);
		glVertex2f(x, y);	//top left corner
		glVertex2f(x + width, y);
		glVertex2f(x + width, y + height );
		glVertex2f(x, y + height );
		glEnd();
	}
	
	//Draw Quad texture with width, height and coordinates
	public static void DrawQuadTex(Texture tex, float x, float y, float width, float height){
		
		//bind texture to openGL
		tex.bind();
		//translate global to local coordinates x, y, z; 
		glTranslatef(x, y, 0); 
		
		//define texture
		glBegin(GL_QUADS);
		
		glTexCoord2f(0 , 0);
		glVertex2f(0, 0);
		
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		
		glEnd();
		glLoadIdentity();
	}
	
	//implementing drawing with rotation
	public static void DrawQuadTexRot(Texture tex, float x, float y, float width, float height, float angle){
		
		//bind texture to openGL
		tex.bind();
		//translate global to local coordinates x, y, z; 
		glTranslatef(x + width / 2, y + height / 2, 0);
		//rotate
		glRotatef(angle, 0, 0, 1);
		//translate back to original origin
		glTranslatef(- width / 2, - height / 2, 0);
		
		//define texture
		glBegin(GL_QUADS);
		
		glTexCoord2f(0 , 0);
		glVertex2f(0, 0);
		
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		
		glEnd();
		glLoadIdentity();
	}
	
	public static Texture LoadTexture(String path, String fileType){
		
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try{
			tex = TextureLoader.getTexture(fileType, in );
		} catch (IOException e){
			System.out.println("LoadTexture error");
			e.printStackTrace();
		}
		return tex;
	}
	
	public static Texture QuickLoad(String name){
		Texture tex = null;
		tex = LoadTexture("./res/" + name + ".png" , "PNG");
		return tex;
	}
}