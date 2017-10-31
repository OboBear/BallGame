package com.me.obo.ballgame.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by obo on 2017/10/31.
 * Email:obo1993@gmail.com
 */

public class RickerManager {
    Paint paintCircleBack = new Paint();
    Paint paintCircleFront = new Paint();
    Matrix matrix = new Matrix();
    private float backRadius;
    private float frontRadius;
    public RickerManager(float backRadius, float frontRadius) {
        paintCircleBack.setColor(Color.DKGRAY);
        paintCircleBack.setAntiAlias(true);
        paintCircleFront.setColor(Color.LTGRAY);
        paintCircleFront.setAntiAlias(true);
        this.backRadius = backRadius;
        this.frontRadius = frontRadius;
    }

    public void draw(Canvas canvas, PointF positionBack, PointF positionFor) {
        canvas.setMatrix(matrix);
        canvas.drawCircle(positionBack.x, positionBack.y  , backRadius, paintCircleBack);
        canvas.drawCircle(positionFor.x, positionFor.y, frontRadius, paintCircleFront);
    }
}
