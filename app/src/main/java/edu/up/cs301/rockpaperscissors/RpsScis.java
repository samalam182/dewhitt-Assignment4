package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
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
    public void draw(Paint objColor, Canvas c) {
        Paint SPaint = objColor;

        Path p = new Path();
        p.moveTo(xPos + 0*xSize, yPos + 0*ySize);
        p.lineTo(xPos + 9*xSize, yPos + 9*ySize);
        p.lineTo(xPos + 12*xSize, yPos + 16*ySize);
        p.lineTo(xPos + 14*xSize, yPos + 23*ySize);
        p.lineTo(xPos + 24*xSize, yPos + 23*ySize);
        p.lineTo(xPos + 16*xSize, yPos + 16*ySize);
        p.lineTo(xPos + 25*xSize, yPos + 17*ySize);
        p.lineTo(xPos + 25*xSize, yPos + 12*ySize);
        p.lineTo(xPos + 17*xSize, yPos + 11*ySize);
        p.lineTo(xPos + 0*xSize, yPos + 7*ySize);
        p.lineTo(xPos + 0*xSize, yPos + 0*ySize);

        Path p2 = new Path();
        p2.moveTo(xPos + 9*xSize, yPos + 9*ySize);
        p2.lineTo(xPos + 0*xSize, yPos + 7*ySize);
        p2.lineTo(xPos + 0*xSize, yPos + 12*ySize);
        p2.lineTo(xPos + 12*xSize, yPos + 16*ySize);
        p2.lineTo(xPos + 9*xSize, yPos + 9*ySize);

        Path h1 = new Path();
        h1.moveTo(xPos + 13*xSize, yPos + 17*ySize);
        h1.lineTo(xPos + 15*xSize, yPos + 17*ySize);
        h1.lineTo(xPos + 18*xSize, yPos + 20*ySize);
        h1.lineTo(xPos + 16*xSize, yPos + 20*ySize);
        h1.lineTo(xPos + 13*xSize, yPos + 17*ySize);

        Path h2 = new Path();
        h2.moveTo(xPos + 17*xSize, yPos + 13*ySize);
        h2.lineTo(xPos + 22*xSize, yPos + 14*ySize);
        h2.lineTo(xPos + 22*xSize, yPos + 16*ySize);
        h2.lineTo(xPos + 18*xSize, yPos + 14*ySize);
        h2.lineTo(xPos + 17*xSize, yPos + 13*ySize);

        SPaint.setColor(Color.GRAY);
        c.drawPath(p, SPaint);
        c.drawPath(p2, SPaint);

        SPaint.setColor(Color.rgb(140,180,255));
        c.drawPath(h1, SPaint);
        c.drawPath(h2, SPaint);

    }
}
