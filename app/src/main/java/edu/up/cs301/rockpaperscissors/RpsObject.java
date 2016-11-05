package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * rock-paper-scissors animation
 *
 * @author Steve Vegdahl
 * @author Samuel DeWhitt
 * @version November 2016
 *
 * Abstact Class that defines necessary parameters and methods for Paper, Rock, and Scissors
 */


public abstract class RpsObject {

    //instance variables
    protected float xPos;
    protected float yPos;
    protected float xSpd;
    protected float ySpd;
    protected float xSize;
    protected float ySize;
    protected float gravityX;
    protected float gravityY;
    protected boolean destroyed;

    //necessary contructor for the subclasses
    public RpsObject(){}

    public RpsObject(float xP, float yP, float xS, float yS, float xSz, float ySz){
        //Initialize all the variables based on the contructor
        xPos = xP;
        yPos = yP;
        xSpd = xS;
        ySpd = yS;
        xSize = xSz;
        ySize = ySz;
        destroyed = false;
        gravityX = 0;
        gravityY = 0;

        }

    //whenever the animator detects that the object hits the left or right wall
    //it reverses direction and slows down by 10%
    public void bounceX(){ xSpd = -(float)(0.9*xSpd); }

    //whenever the animator detects that the object hits the ground
    //it reverses direction and slows down by 10%
    public void bounceY(){ ySpd = -(float) (0.9*ySpd); }

    //sets the destroy variable to true to show it's destroyed
    public void destroy(){ destroyed = true; }

    //checks to see if the object is destroyed
    public boolean isDestroyed(){ return destroyed; }

    //generic draw method will be implemented further in subclasses
    public void draw(Paint objColor, Canvas c){}

    //method called when one object collides with another and the necessary one is destroyed
    public void absorb(RpsObject obj){
        xSize += obj.getSizeX()/50;
        ySize += obj.getSizeY()/50;
        xSpd += obj.getVelX()/2;
        ySpd += obj.getVelY()/2;
    }

    //called to update the objects speed and position based on
    //'natural' gravity and finger gravity.
    public void ticked(){
        xPos += xSpd;
        yPos += ySpd;

        ySpd += 0.8;

        xSpd += gravityX*12;
        ySpd += gravityY*12;
    }

    // below are a bunch of getter and setter methods
    //25 is used because I designed each object as it's smallest version 25x25 pixels
    public float getSizeX(){
        return 25*xSize;
    }
    public float getSizeY(){
        return 25*ySize;
    }

    public float getPosX(){
        return xPos;
    }

    public float getPosY(){
        return yPos;
    }
    public float getVelX(){
        return xSpd;
    }

    public float getVelY(){
        return ySpd;
    }

    public void setxPos(float newX){
        xPos = newX;
    }

    public void setyPos(float newY){
        yPos = newY;
    }

    public void setxGrav(float newXGrav){
        gravityX += newXGrav;
    }

    public void setyGrav(float newYGrav){
        gravityY += newYGrav;
    }
    public void reSetGrav(){
        gravityX = 0;
        gravityY = 0;
    }






























}
