package edu.up.cs301.rockpaperscissors;

import android.graphics.*;
import android.util.Log;

/**
 * Created by Samuel on 11/3/2016.
 */

public class RpsRock extends RpsObject {

    public RpsRock(float xP, float yP, float xS, float yS, float xSz, float ySz){
        super(xP, yP, xS, yS, xSz, ySz);
    }

    @Override
    public void draw(Paint objColor, Canvas c) {
        Paint RPaint = objColor;
        RPaint.setColor(Color.BLACK);

        Path p = new Path();
        p.moveTo(xPos + 7*xSize, yPos + 0*ySize);
        p.lineTo(xPos + 0*xSize, yPos + 13*ySize);
        p.lineTo(xPos + 5*xSize, yPos + 25*ySize);
        p.lineTo(xPos + 17*xSize, yPos + 25*ySize);
        p.lineTo(xPos + 25*xSize, yPos + 12*ySize);
        p.lineTo(xPos + 21*xSize, yPos + 0*ySize);
        p.lineTo(xPos + 14*xSize, yPos + 2*ySize);
        p.lineTo(xPos + 7*xSize, yPos + 0*ySize);

        c.drawPath(p, RPaint);

    }
}
