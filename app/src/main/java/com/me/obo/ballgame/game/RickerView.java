package com.me.obo.ballgame.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
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
    PointF position;


    public RickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintCircleBack.setColor(Color.DKGRAY);
        paintCircleBack.setAntiAlias(true);
        paintCircleFront.setColor(Color.LTGRAY);
        paintCircleFront.setAntiAlias(true);
        position = new PointF(0, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        position.x = getMeasuredWidth()/2;
        position.y = getMeasuredHeight()/2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                position.x = event.getX();
                position.y = event.getY();
                invalidate();
                return true;

            case MotionEvent.ACTION_MOVE:
                position.x = event.getX();
                position.y = event.getY();
                invalidate();

                break;

            case MotionEvent.ACTION_UP:
                position.x = getWidth()/2;
                position.y = getHeight()/2;
                invalidate();
                break;

            default:
        }
        return super.onTouchEvent(event);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/3, paintCircleBack);
        canvas.drawCircle(position.x, position.y, getWidth()/6, paintCircleFront);
    }
}
