package edu.up.cs301.rockpaperscissors;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
import java.lang.Math;

import edu.up.cs301.animation.AnimationSurface;
import edu.up.cs301.animation.Animator;

import static android.R.attr.button;
import static android.util.FloatMath.sqrt;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;


/**
 * rock-paper-scissors animation
 * 
 * @author Steve Vegdahl
 * @version August 2016
 */
public class RpsAnimator extends RpsActivity implements Animator{

	// instance variables
	private int count = 0; // counts the number of logical clock ticks
	private boolean goBackwards = false; // whether clock is ticking backwards
	private boolean gravity = false;
	private int gravityX = 0;
	private int gravityY = 0;
	ArrayList<RpsObject> RpsList = new ArrayList<RpsObject>();
	RpsObject tempOne;
	RpsObject tempTwo;

	private final int rightSide = 2048;
	private final int leftSide = 0;
	private final int bottom = 1186;

	Button rock;
	Button scis;
	Button paper;
	Button rand;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rps);
		rock = (Button)findViewById(R.id.rockButt);
		scis = (Button)findViewById(R.id.scisButt);
		paper = (Button)findViewById(R.id.paperButt);
		rand = (Button)findViewById(R.id.randButt);
	}
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

			//Log.i("List Size: ", "" + RpsList.size());
			for(int i = 0; i<RpsList.size(); i++) {
				tempOne = RpsList.get(i);



				if(tempOne.getPosX() < leftSide){
					tempOne.setxPos(1);
					tempOne.bounceX();
				}
				if(tempOne.getPosX()+tempOne.getSizeX() > rightSide){
					tempOne.setxPos(rightSide - tempOne.getSizeX() - 1);
					tempOne.bounceX();
				}

				if((tempOne.getPosY()+tempOne.getSizeY() > bottom)){
					tempOne.setyPos(bottom - tempOne.getSizeY() - 1);
					tempOne.bounceY();
				}

				for(int j = 0; j<RpsList.size(); j++){
					tempTwo = RpsList.get(j);
					if(!tempOne.isDestroyed() && !tempTwo.isDestroyed()) {


						if ((tempOne != tempTwo) && overlaps(tempOne, tempTwo)) {

							if (tempOne instanceof RpsPaper && tempTwo instanceof RpsRock) {
								tempTwo = (RpsRock)RpsList.get(j);
								tempTwo.destroy();
								tempOne.absorb(tempTwo);
								return;
							}
						if(tempOne instanceof RpsPaper && tempTwo instanceof RpsScis){
							tempOne = (RpsPaper)RpsList.get(i);
							tempOne.destroy();
							tempTwo.absorb(tempOne);
						}
//						/////////////////////////////
						if(tempOne instanceof RpsScis && tempTwo instanceof RpsRock){
							tempOne = (RpsScis)RpsList.get(i);
							tempOne.destroy();
							tempTwo.absorb(tempOne);
						}
						if(tempOne instanceof RpsScis && tempTwo instanceof RpsPaper){
							tempTwo = (RpsPaper)RpsList.get(j);
							tempTwo.destroy();
							tempOne.absorb(tempTwo);
						}
//						////////////////////////////////
						if(tempOne instanceof RpsRock && tempTwo instanceof RpsPaper){
							tempOne = (RpsRock)RpsList.get(i);
							tempOne.destroy();
							tempTwo.absorb(tempOne);
						}
						if(tempOne instanceof RpsRock && tempTwo instanceof RpsScis){
							tempTwo = (RpsScis)RpsList.get(j);
							tempTwo.destroy();
							tempOne.absorb(tempTwo);
						}
//						/////////////////////////////////
						if(tempOne instanceof RpsPaper && tempTwo instanceof RpsPaper){
							sameCollision((RpsPaper)tempOne, (RpsPaper)tempTwo);
						}
						if(tempOne instanceof RpsRock && tempTwo instanceof RpsRock){
							sameCollision((RpsRock)tempOne, (RpsRock)tempTwo);
						}
						if(tempOne instanceof RpsScis && tempTwo instanceof RpsScis){
							sameCollision((RpsScis)tempOne, (RpsScis)tempTwo);
						}
						}
					}

				}

				if(!tempOne.isDestroyed()) {
					tempOne.ticked();
					tempOne.draw(p, g);
				}
			}
		}

	}

	public void sameCollision(RpsObject r1, RpsObject r2){
		if((r1.getSizeX()*r1.getSizeY() > (r2.getSizeX()*r2.getSizeY()))){
			r2.destroy();
			r1.absorb(r2);
		}
		else{
			r1.destroy();
			r2.absorb(r1);
		}
	}

	public boolean overlaps(RpsObject t1, RpsObject t2){
		if(t1.getPosX() > t2.getPosX() && t1.getPosX() < (t2.getPosX()+t2.getSizeX())){

			if(t1.getPosY() > t2.getPosY() && t1.getPosY() < (t2.getPosY()+t2.getSizeY())) {
				return true;
			}

			if((t1.getPosY()+t1.getSizeY()) > t2.getPosY()
					&& (t1.getPosY()+t1.getSizeY()) < (t2.getPosY()+t2.getSizeY())){
				return true;
			}
		}

		if((t1.getPosX()+t1.getSizeX()) > t2.getPosX()
				&& (t1.getPosX()+t1.getSizeX()) < (t2.getPosX()+t2.getSizeX())){

			if(t1.getPosY() > t2.getPosY() && t1.getPosY() < (t2.getPosY()+t2.getSizeY())) {
				return true;
			}

			if((t1.getPosY()+t1.getSizeY()) > t2.getPosY()
					&& (t1.getPosY()+t1.getSizeY()) < (t2.getPosY()+t2.getSizeY())){
				return true;
			}

		}

		return false;
	}

	/**
	 * Tells that we never pause.
	 *
	 * @return indication of whether to pause
	 */
	protected void addObject(int amount){

		int tempAmount = amount;
		int amountRock = (int)(Math.random()*tempAmount);
		tempAmount -=amountRock;
		int amountScissor = (int)(Math.random()*tempAmount);
		tempAmount -=amountScissor;
		int amountPaper = tempAmount;

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
			RpsObject tempAdd;
			if (type == 0) {
				tempAdd = new RpsRock(randX, randY, randVelX, randVelY, randSizeX, randSizeY);
				RpsList.add(tempAdd);
			} else if (type == 1) {
				tempAdd = new RpsScis(randX, randY, randVelX, randVelY, randSizeX, randSizeY);
				RpsList.add(tempAdd);
			} else {
				tempAdd = new RpsPaper(randX, randY, randVelX, randVelY, randSizeX, randSizeY);
				RpsList.add(tempAdd);
			}
		}
	}

	public void gravitate(float xFing, float yFing){
		float a, b, c, grav;
		for(int k = 0; k<RpsList.size(); k++){
			if(xFing == -1 && yFing == -1){
				RpsList.get(k).reSetGrav();
			}
			else if(RpsList.size() > 0){
				a = xFing - RpsList.get(k).getPosX();
				b = yFing - RpsList.get(k).getPosY();
				c = (float) Math.sqrt((a*a + b*b));
				grav = (float) (1/(c+0.1));
				if(a > 0){
					RpsList.get(k).setxGrav(grav);
				}
				if(a < 0){
					RpsList.get(k).setxGrav(-grav);
				}

				if(b > 0){
					RpsList.get(k).setyGrav(grav);
				}
				if(b < 0){
					RpsList.get(k).setyGrav(-grav);
				}
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


	public void onTouch(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		if(event.getAction() == ACTION_DOWN || event.getAction() == ACTION_MOVE){
			gravitate(x, y);
		}
		else if(event.getAction() == ACTION_UP){
			//gravitate(-1, -1);
		}


		//gravitate(x, y);


	}




}//class RpsAnimator
