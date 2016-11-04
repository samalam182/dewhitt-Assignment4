package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

/**
 * Created by Samuel on 11/4/2016.
 */

public class RpsPaper extends RpsObject {

    public RpsPaper(float xP, float yP, float xS, float yS, float xSz, float ySz){
        super(xP, yP, xS, yS, xSz, ySz);
    }

    @Override
    public void draw(Paint objColor, Canvas c) {
//        Log.i("Position", "" + xPos + " " + yPos);
//        Log.i("Velocity", "" + xSpd + " " + ySpd);
//        Log.i("Size", "" + xSize + " " + ySize);
        Paint PPaint = objColor;
        PPaint.setColor(Color.WHITE);

        Path p = new Path();
        p.moveTo(xPos + 3*xSize, yPos + 0*ySize);
        p.lineTo(xPos + 25*xSize, yPos + 0*ySize);
        p.lineTo(xPos + 22*xSize, yPos + 25*ySize);
        p.lineTo(xPos + 0*xSize, yPos + 25*ySize);
        p.lineTo(xPos + 3*xSize, yPos + 0*ySize);

        c.drawPath(p, PPaint);

    }
}
