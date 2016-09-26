package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

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

	/**
	 * Interval between animation frames: .03 seconds (i.e., about 33 times
	 * per second).
	 *
	 * @return the time interval between frames, in milliseconds.
	 */
	public int interval() {
		return 30;
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
		// bump our count either up or down by one, depending on whether
		// we are in "backwards mode".
		if (goBackwards) {
			count--;
		}
		else {
			count++;
		}

		// Determine the pixel position of our ball.  Multiplying by 15
		// has the effect of moving 15 pixel per frame.  Modding by minimum
		// canvas-size dimension (with the appropriate correction if the value
		// was negative) has the effect of "wrapping around" when we get to

		int wrap = Math.min(g.getWidth(), g.getHeight());
		// either end
		int num = (count*15)%wrap;
		if (num < 0) num += wrap;

		// Draw the ball in the correct position.
		Paint redPaint = new Paint();
		redPaint.setColor(Color.RED);
		g.drawCircle(num, num, 60, redPaint);
		redPaint.setColor(0xff0000ff);
	}

	/**
	 * Tells that we never pause.
	 *
	 * @return indication of whether to pause
	 */
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
			goBackwards = !goBackwards;
		}
	}

}//class RpsAnimator
