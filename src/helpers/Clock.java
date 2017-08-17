package helpers;

import org.lwjgl.Sys;

// Measuring time
public class Clock {

	public static boolean paused = false;
	//lastFrame = getTime()
	public static long lastFrame, totalTime;
	public static float d = 0, multiplier = 1;
	
	
	/**
	 * At the beginning of the run lastFrame is set to 0 which is a long way off 
	 * currentTime and in my case I ended up with delta being an insanely large
	 *  negative int right off the bat. I got around this by changing to 
	 *  public static long lastFrame = getTime(), totalTime;
	 */
	public static long getTime() {
		//		Time (s) / FPS
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
	/**Delta variable is used in many game engines
	 * @return time between right now and last update of the game
	 */
	public static float getDelta(){
		long currentTime = getTime();
		// delta = current - lastFrame time
		int delta = (int) (currentTime - lastFrame);
		//after new delta measure, lastFrame time = Now time
		lastFrame = getTime();
		/*this if-block helps clears situation where delta values are too big, 
		meaning when for long time the game isn't redrawn. Just automatically
		cut delta to small 0,5f value
		*/
		if(delta*0.001f > 0.05f){
			return 0.05f;
		}
		return delta * 0.001f;
	}
	
	//Detla is the time between two updates of the game 
	public static float Delta(){
		if(paused){
			return 0;
		} else {
			return d * multiplier;
		}
	}
	
	public static float TotalTime(){
		return totalTime;
	}
	
	public static float Multiplier(){
		return multiplier;
	}
	
	public static void update(){
		d = getDelta();
		totalTime += d;
	}
	
	//Change game tempo within limits [0, 7]
	public static void ChangeMultiplier(float change){
		if(multiplier + change > 0.2f && multiplier + change < 7.0f){
			multiplier += change;
		} else {
			//don't allow that much change
		}
	}
	
	//Toggle pause
	public static void GamePause(){
		if(paused){
			paused = false;
		} else {
			paused = true;
		}
	}
	
	public static boolean isPaused() {
		return paused;
	}
	
	public static void setPaused(boolean paused) {
		Clock.paused = paused;
	}

	public static void GameOver(){
		paused = true;
	}
}
