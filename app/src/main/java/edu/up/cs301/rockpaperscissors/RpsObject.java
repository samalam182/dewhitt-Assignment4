package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Samuel on 11/3/2016.
 */


public abstract class RpsObject {
    protected float xPos;
    protected float yPos;
    protected float xSpd;
    protected float ySpd;
    protected float xSize;
    protected float ySize;
    protected boolean destroyed;

    public RpsObject(){}

    public RpsObject(float xP, float yP, float xS, float yS, float xSz, float ySz){
        xPos = xP;
        yPos = yP;
        xSpd = xS;
        ySpd = yS;
        xSize = xSz;
        ySize = ySz;
        destroyed = false;
    }

    //whenever the animator detects that the object hits the left or right wall
    //it reverses direction and slows down by 10%
    public void bounceX(){ xSpd = -(float)(0.9*xSpd); }

    //whenever the animator detects that the object hits the ground
    //it reverses direction and slows down by 10%
    public void bounceY(){ ySpd = -(float) (0.9*ySpd); }

    public void isDestroyed(){ destroyed = true; }

    public void draw(Paint objColor, Canvas c){}

    public void tick(){
        xPos += xSpd;
        yPos += ySpd;
    }

}
