package edu.up.cs301.rockpaperscissors;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Samuel on 11/4/2016.
 */

public class RpsScis extends RpsObject {

    public RpsScis(float xP, float yP, float xS, float yS, float xSz, float ySz){
        super(xP, yP, xS, yS, xSz, ySz);
    }

    @Override
    public void draw(Paint objColor) {


        Path p = new Path();
        p.moveTo(xPos + 5*xSize, yPos + 5*ySize);
        p.lineTo(xPos + 2*xSize, yPos + 13*ySize);
        p.lineTo(xPos + 5*xSize, yPos + 20*ySize);
        p.lineTo(xPos + 13*xSize, yPos + 20*ySize);
        p.lineTo(xPos + 20*xSize, yPos + 15*ySize);
        p.lineTo(xPos + 17*xSize, yPos + 5*ySize);
        p.lineTo(xPos + 12*xSize, yPos + 7*ySize);
        p.lineTo(xPos + 5*xSize, yPos + 5*ySize);



    }
}
