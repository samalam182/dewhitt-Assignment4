package edu.up.cs301.rockpaperscissors;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.animation.Animator;

import static android.R.attr.button;


/**
 * rock-paper-scissors animation
 * 
 * @author Steve Vegdahl
 * @version August 2016
 */
public class RpsAnimator implements Animator{

	// instance variables
	private int count = 0; // counts the number of logical clock ticks
	private boolean goBackwards = false; // whether clock is ticking backwards
	private boolean gravity = false;
	private int gravityX = 0;
	private int gravityY = 0;
	ArrayList<RpsObject> RpsList = new ArrayList<RpsObject>();
	RpsObject temp;
	int cType = 0;
	int cNum = 0;
	private final int rightSide = 2048;
	private final int leftSide = 0;
	private final int bottom = 1186;


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
	 * Action to perform on clock tick
	 *
	 * @param g the graphics object on which to draw
	 */
	public void tick(Canvas g) {

		Paint p = new Paint();
		p.setColor(Color.BLUE);

		if(RpsList.size() > 0) {

			Log.i("List Size: ", "" + RpsList.size());
			for(int i = 0; i<RpsList.size(); i++) {
				temp = RpsList.get(i);

				if(temp.getPosX() < leftSide || (temp.getPosX()+temp.getSizeX() > rightSide)){
					temp.bounceX();
				}

				if((temp.getPosY()+temp.getSizeY() > bottom)){
					temp.bounceY();
				}

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
	protected void addObject(int amount){

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

	protected void addObject(int amount, int type){
		float randX, randY, randVelX, randVelY, randSizeX, randSizeY;

		for (int i = 0; i < amount; i++) {
			randX = (float) (100 + 1500 * Math.random());
			randY = (float) (100 + 1000 * Math.random());
			randVelX = (float) (20 * Math.random() - 14);
			randVelY = (float) (20 * Math.random() - 14);
			randSizeX = (float) (2 + (4 * Math.random()));
			randSizeY = (float) (2 + (4 * Math.random()));
			RpsObject temp;
			if (type == 0) {
				temp = new RpsRock(randX, randY, randVelX, randVelY, randSizeX, randSizeY);
				RpsList.add(temp);
			} else if (type == 1) {
				temp = new RpsScis(randX, randY, randVelX, randVelY, randSizeX, randSizeY);
				RpsList.add(temp);
			} else {
				temp = new RpsPaper(randX, randY, randVelX, randVelY, randSizeX, randSizeY);
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

		}
	}


}//class RpsAnimator
