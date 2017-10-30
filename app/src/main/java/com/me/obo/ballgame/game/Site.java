package com.me.obo.ballgame.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by obo on 2017/10/28.
 * Email:obo1993@gmail.com
 */

public class Site {
    public float width;
    public float height;
    private static final float SEPARATE_LINE_DISTANCE = 10;
    Paint paint;
    public Site(float width, float height) {
        this.width = width;
        this.height = height;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
    }

    public void draw(Canvas canvas, RectF visibleRect) {
        for (int x = (int) (visibleRect.left/SEPARATE_LINE_DISTANCE); x * SEPARATE_LINE_DISTANCE < visibleRect.right; x++) {
            canvas.drawLine(x * SEPARATE_LINE_DISTANCE, visibleRect.top, x * SEPARATE_LINE_DISTANCE, visibleRect.bottom, paint);
        }
        for (int y = (int) (visibleRect.top/SEPARATE_LINE_DISTANCE); y * SEPARATE_LINE_DISTANCE < visibleRect.bottom; y++) {
            canvas.drawLine(visibleRect.left, y * SEPARATE_LINE_DISTANCE, visibleRect.right, y * SEPARATE_LINE_DISTANCE, paint);
        }
    }
}
