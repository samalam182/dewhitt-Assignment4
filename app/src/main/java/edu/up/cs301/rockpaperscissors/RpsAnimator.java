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
 * @author Samuel DeWhitt
 * @version November 2016
 */
public class RpsAnimator extends RpsActivity implements Animator {

    // instance variables
    private int count = 0; // counts the number of logical clock ticks
    private boolean goBackwards = false; // whether clock is ticking backwards
    private boolean gravity = false;
    private int gravityX = 0;
    private int gravityY = 0;
    ArrayList<RpsObject> RpsList = new ArrayList<RpsObject>();
    RpsObject tempOne;
    RpsObject tempTwo;

    //final variables to designate the size of the tablet
    private final int rightSide = 2048;
    private final int leftSide = 0;
    private final int bottom = 1186;

    //buttons
    Button rock;
    Button scis;
    Button paper;
    Button rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rps);

        //create all the buttons
        rock = (Button) findViewById(R.id.rockButt);
        scis = (Button) findViewById(R.id.scisButt);
        paper = (Button) findViewById(R.id.paperButt);
        rand = (Button) findViewById(R.id.randButt);

        //creates 20 random objects in the array

    }

    /**
     * Interval between animation frames: .03 seconds (i.e., about 33 times
     * per second).
     *
     * @return the time interval between frames, in milliseconds.
     */

    //set interval to 33ms to get that solid 30FPS
    public int interval() {
        return 33;
    }

    /**
     * The background color: a light blue.
     *
     * @return the background color onto which we will draw the image.
     */
    //didn't mess with background color
    public int backgroundColor() {
        // create/return the background color
        return Color.rgb(140, 180, 255); // light blue
    }

    /**
     * Action to perform on clock tick
     *
     * @param g the graphics object on which to draw
     */
    public void tick(Canvas g) {
        if(RpsList.size() == 0){
            addObject(20);
        }
        //paint variable to be handed to each object so they can create themselves
        //in whatever pretty color they want to.
        Paint p = new Paint();

        //if the array is empty don't do anything
        if (RpsList.size() > 0) {

            //iterate through all objects in the arrayList
            for (int i = 0; i < RpsList.size(); i++) {
                //set temp variable equal to the item at index i
                tempOne = RpsList.get(i);

                //checks to see if the object has gone past the left edge
                //resets it's x-coordinate so that it doesn't clip
                //and get stuck in the wall
                if (tempOne.getPosX() < leftSide) {
                    tempOne.setxPos(1);
                    tempOne.bounceX();
                }

                //does the same thing on the right side of the screen
                if (tempOne.getPosX() + tempOne.getSizeX() > rightSide) {
                    tempOne.setxPos(rightSide - tempOne.getSizeX() - 1);
                    tempOne.bounceX();
                }

                //does the same thing on the bottom of the screen with the y-coordinate
                if ((tempOne.getPosY() + tempOne.getSizeY() > bottom)) {
                    tempOne.setyPos(bottom - tempOne.getSizeY() - 1);
                    tempOne.bounceY();
                }

                //now iterates over the rest of the array and checks the object at index
                //i against all the other objects given by index j
                for (int j = 0; j < RpsList.size(); j++) {
                    //sets tempTwo to the arrayList for comparisons
                    tempTwo = RpsList.get(j);

                    //if either of the objects is destroyed, skip over the whole thing
                    //otherwise check all collisions
                    if (!tempOne.isDestroyed() && !tempTwo.isDestroyed()) {

                        //skip over checking one object against itself
                        //and don't do anything unless the two given objects are
                        //actually overlapping
                        if ((tempOne != tempTwo) && overlaps(tempOne, tempTwo)) {
                            //checks rock against paper
                            //paper always wins, absorbs rock, and destroys rock
                            if (tempOne instanceof RpsPaper && tempTwo instanceof RpsRock) {
                                tempTwo = (RpsRock) RpsList.get(j);
                                tempTwo.destroy();
                                tempOne.absorb(tempTwo);
                                return;
                            }
                            //checks paper against scissors
                            //scissors always wins, absorbs paper, destroys paper
                            if (tempOne instanceof RpsPaper && tempTwo instanceof RpsScis) {
                                tempOne = (RpsPaper) RpsList.get(i);
                                tempOne.destroy();
                                tempTwo.absorb(tempOne);
                            }
                            //checks scissors against rock
                            //rock always wins, absorbs scissors, destroys scissors
                            if (tempOne instanceof RpsScis && tempTwo instanceof RpsRock) {
                                tempOne = (RpsScis) RpsList.get(i);
                                tempOne.destroy();
                                tempTwo.absorb(tempOne);
                            }
                            //checks scissors against paper in the other order
                            //scissors still wins, and does its thing
                            if (tempOne instanceof RpsScis && tempTwo instanceof RpsPaper) {
                                tempTwo = (RpsPaper) RpsList.get(j);
                                tempTwo.destroy();
                                tempOne.absorb(tempTwo);
                            }
                            //checks rock against paper in the other order
                            //paper still wins and does its thing
                            if (tempOne instanceof RpsRock && tempTwo instanceof RpsPaper) {
                                tempOne = (RpsRock) RpsList.get(i);
                                tempOne.destroy();
                                tempTwo.absorb(tempOne);
                            }
                            //checks rock against scissors in the other order
                            //rock always wins and does its thin
                            if (tempOne instanceof RpsRock && tempTwo instanceof RpsScis) {
                                tempTwo = (RpsScis) RpsList.get(j);
                                tempTwo.destroy();
                                tempOne.absorb(tempTwo);
                            }
                            //the next three checks an object against the same type of object
                            //uses sameCollision method to check which one is bigger
                            //absorbs the smaller one and destroys it
                            if (tempOne instanceof RpsPaper && tempTwo instanceof RpsPaper) {
                                sameCollision((RpsPaper) tempOne, (RpsPaper) tempTwo);
                            }
                            if (tempOne instanceof RpsRock && tempTwo instanceof RpsRock) {
                                sameCollision((RpsRock) tempOne, (RpsRock) tempTwo);
                            }
                            if (tempOne instanceof RpsScis && tempTwo instanceof RpsScis) {
                                sameCollision((RpsScis) tempOne, (RpsScis) tempTwo);
                            }
                        }
                    }

                }
                //finally, after all checks are made go ahead and move and display all the objects
                //that are not destroyed
                if (!tempOne.isDestroyed()) {
                    tempOne.ticked();
                    tempOne.draw(p, g);
                }
            }
        }

    }

    //method to check size. If 1 is bigger than 2, destroy and absorb 2
    //or do the opposite
    public void sameCollision(RpsObject r1, RpsObject r2) {
        if ((r1.getSizeX() * r1.getSizeY() > (r2.getSizeX() * r2.getSizeY()))) {
            r2.destroy();
            r1.absorb(r2);
        } else {
            r1.destroy();
            r2.absorb(r1);
        }
    }

    //checks all 4 cases of overlap: left edge, right edge, top edge, and bottom edge
    //and returns true if any of those edges intercept another object
    public boolean overlaps(RpsObject t1, RpsObject t2) {
        if (t1.getPosX() > t2.getPosX() && t1.getPosX() < (t2.getPosX() + t2.getSizeX())) {

            if (t1.getPosY() > t2.getPosY() && t1.getPosY() < (t2.getPosY() + t2.getSizeY())) {
                return true;
            }

            if ((t1.getPosY() + t1.getSizeY()) > t2.getPosY()
                    && (t1.getPosY() + t1.getSizeY()) < (t2.getPosY() + t2.getSizeY())) {
                return true;
            }
        }

        if ((t1.getPosX() + t1.getSizeX()) > t2.getPosX()
                && (t1.getPosX() + t1.getSizeX()) < (t2.getPosX() + t2.getSizeX())) {

            if (t1.getPosY() > t2.getPosY() && t1.getPosY() < (t2.getPosY() + t2.getSizeY())) {
                return true;
            }

            if ((t1.getPosY() + t1.getSizeY()) > t2.getPosY()
                    && (t1.getPosY() + t1.getSizeY()) < (t2.getPosY() + t2.getSizeY())) {
                return true;
            }

        }

        return false;
    }

    //given a number of objects, create a random amount of each type
    protected void addObject(int amount) {

        int tempAmount = amount;
        int amountRock = (int) (Math.random() * tempAmount);
        tempAmount -= amountRock;
        int amountScissor = (int) (Math.random() * tempAmount);
        tempAmount -= amountScissor;
        int amountPaper = tempAmount;

        addObject(amountRock, 0);
        addObject(amountScissor, 1);
        addObject(amountPaper, 2);

    }
    //given a number of objects and a type, create that many with random starting
    //positions, velocities, and directions and adds them to the arrayList
    protected void addObject(int amount, int type) {
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


    //ideally when the finger is pressed the item moves towards that object based on 1/d^2
    //also the gravity disappears on ACTION_UP
    public void gravitate(float xFing, float yFing) {
        float a, b, c, grav;
        //if there is no press -1 and -1 will be passed in
        for (int k = 0; k < RpsList.size(); k++) {
            if (xFing == -1 && yFing == -1) {
                //ideally gravity is reset back to zero in the RpsObject
                RpsList.get(k).reSetGrav();
            }
            //otherwise take given x and y finger value and figure out distance between given
            //object and finger. Then move with 1/d^2 gravity towards the finger
            else if (RpsList.size() > 0) {
                a = xFing - RpsList.get(k).getPosX();
                b = yFing - RpsList.get(k).getPosY();
                c = (float) Math.sqrt((a * a + b * b));
                grav = (float) (1 / (c + 0.1));
                if (a > 0) {
                    RpsList.get(k).setxGrav(grav);
                }
                if (a < 0) {
                    RpsList.get(k).setxGrav(-grav);
                }
                if (b > 0) {
                    RpsList.get(k).setyGrav(grav);
                }
                if (b < 0) {
                    RpsList.get(k).setyGrav(-grav);
                }
            }
        }
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

    //when a touch is present record the x and y values to x and y
    //
    public void onTouch(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (event.getAction() == ACTION_DOWN || event.getAction() == ACTION_MOVE) {
            //if the finger is pressed tell gravity to be set based on the
            //fingers position
            gravitate(x, y);
        } else if (event.getAction() == ACTION_UP) {
            //when the finger is not pressed, set gravity to 0
            gravitate(-1, -1);
        }

    }


}//class RpsAnimator
