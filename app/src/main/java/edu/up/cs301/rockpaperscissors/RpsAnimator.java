package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.animation.Animator;


/**
 * rock-paper-scissors animation
 * 
 * @author Steve Vegdahl
 * @version August 2016
 */
public class RpsAnimator implements Animator {

	// instance variables
	private int count = 0; // counts the number of logical clock ticks
	private boolean goBackwards = false; // whether clock is ticking backwards
	private boolean gravity = false;
	private int gravityX = 0;
	private int gravityY = 0;
	ArrayList<RpsObject> RpsList = new ArrayList<RpsObject>();
	int cType = 0;
	int cNum = 0;

	/**
	 * Interval between animation frames: .03 seconds (i.e., about 33 times
	 * per second).
	 *
	 * @return the time interval between frames, in milliseconds.
	 */
	public int interval() {
		return 33;
	}

	/**
	 * The background color: a light blue.
	 *
	 * @return the background color onto which we will draw the image.
	 */
	public int backgroundColor() {
		// create/return the background color
		return Color.rgb(140,180,255); // light blue
	}

	/**
	 * Tells the animation whether to go backwards.
	 *
	 * @param b true iff animation is to go backwards.
	 */
	public void goBackwards(boolean b) {
		// set our instance variable
		goBackwards = b;
	}

	/**
	 * Action to perform on clock tick
	 *
	 * @param g the graphics object on which to draw
	 */
	public void tick(Canvas g) {
		count++;

		Paint p = new Paint();
		p.setColor(Color.BLUE);
		if(RpsList.size() > 0) {
			Log.i("List Size: ", "" + RpsList.size());
			for(int i = 0; i<RpsList.size(); i++) {
				RpsList.get(i).draw(p, g);
				RpsList.get(i).tick();
			}
		}

	}

	/**
	 * Tells that we never pause.
	 *
	 * @return indication of whether to pause
	 */
	public void addObject(int amount){

		int temp = amount;
		int amountRock = (int)(Math.random()*temp);
		temp -=amountRock;
		int amountScissor = (int)(Math.random()*temp);
		temp -=amountScissor;
		int amountPaper = temp;

		addObject(amountRock, 0);
		addObject(amountScissor, 1);
		addObject(amountPaper, 2);

	}

	private void addObject(int amount, int type){
		float randX, randY, randVelX, randVelY, randSizeX, randSizeY;
		for(int i = 0; i<amount; i++){
			randX = (float)(50 + 1900*Math.random());
			randY = (float)(50 + 1400*Math.random());
			randVelX = (float)(16*Math.random() - 8);
			randVelY = (float)(16*Math.random() - 8);
			randSizeX = (float)(2 + (4*Math.random()));
			randSizeY = (float)(2 + (4*Math.random()));
			RpsObject temp;
			if(type == 0){
				temp = new RpsRock(randX, randY, randVelX, randVelY,randSizeX, randSizeY);
				RpsList.add(temp);
			}
			else if(type == 1){
				temp = new RpsScis(randX, randY, randVelX, randVelY,randSizeX, randSizeY);
				RpsList.add(temp);
			}
			else{
				temp = new RpsPaper(randX, randY, randVelX, randVelY,randSizeX, randSizeY);
				RpsList.add(temp);
			}
		}
	}

	public boolean doPause() {
		return false;
	}

	/**
	 * Tells that we never stop the animation.
	 *
	 * @return indication of whether to quit.
	 */
	public boolean doQuit() {
		return false;
	}

	/**
	 * reverse the ball's direction when the screen is tapped
	 */
	public void onTouch(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{

			//cType = 0;
			if(cType < 2) {
				cType= cType+1;
			}
			else{
				cType = 0;
			}

			addObject(1, cType);
		}
	}

}//class RpsAnimator
