package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

/**
 * rock-paper-scissors animation
 *
 * @author Steve Vegdahl
 * @author Samuel DeWhitt
 * @version November 2016
 */

public class RpsPaper extends RpsObject {

    public RpsPaper(float xP, float yP, float xS, float yS, float xSz, float ySz){
        super(xP, yP, xS, yS, xSz, ySz);
    }
    // sets a paint, creates a path, and then draws the path and paint
    @Override
    public void draw(Paint objColor, Canvas c) {
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
