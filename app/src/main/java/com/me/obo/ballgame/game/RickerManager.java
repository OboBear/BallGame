package com.me.obo.ballgame.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by obo on 2017/10/31.
 * Email:obo1993@gmail.com
 */

public class RickerManager {
    Paint paintCircleBack = new Paint();
    Paint paintCircleFront = new Paint();
    float backRadius;
    float frontRadius;
    public RickerManager() {
        paintCircleBack.setColor(Color.DKGRAY);
        paintCircleBack.setAntiAlias(true);
        paintCircleFront.setColor(Color.LTGRAY);
        paintCircleFront.setAntiAlias(true);
    }

    public void draw(Canvas canvas, PointF positionBack, PointF positionFor) {
        canvas.drawCircle(positionBack.x, positionBack.y  , backRadius, paintCircleBack);
        canvas.drawCircle(positionFor.x, positionFor.y, frontRadius, paintCircleFront);
    }
}
