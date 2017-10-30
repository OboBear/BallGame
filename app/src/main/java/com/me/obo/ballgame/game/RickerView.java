package com.me.obo.ballgame.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by obo on 2017/10/30.
 */

public class RickerView extends View {

    Paint paintCircleBack = new Paint();
    Paint paintCircleFront = new Paint();


    public RickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintCircleBack.setColor(Color.DKGRAY);
        paintCircleFront.setColor(Color.LTGRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
        }
        return super.onTouchEvent(event);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2, paintCircleBack);
        canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2, paintCircleBack);

    }
}
