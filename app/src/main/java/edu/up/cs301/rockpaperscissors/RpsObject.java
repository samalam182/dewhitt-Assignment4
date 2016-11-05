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
    protected float gravityX;
    protected float gravityY;
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
        gravityX = 0;
        gravityY = 0;
    }

    //whenever the animator detects that the object hits the left or right wall
    //it reverses direction and slows down by 10%
    public void bounceX(){ xSpd = -(float)(0.9*xSpd); }

    //whenever the animator detects that the object hits the ground
    //it reverses direction and slows down by 10%
    public void bounceY(){ ySpd = -(float) (0.8*ySpd); }

    public void destroy(){ destroyed = true; }

    public boolean isDestroyed(){ return destroyed; }

    public void draw(Paint objColor, Canvas c){}

    public void absorb(RpsObject obj){
        xSize += obj.getSizeX()/50;
        ySize += obj.getSizeY()/50;
        xSpd += obj.getVelX()/2;
        ySpd += obj.getVelY()/2;
    }

    public void ticked(){
        xPos += xSpd;
        yPos += ySpd;

        ySpd += 0.8;

        xSpd += gravityX*30;
        ySpd += gravityY*30;
    }

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
