package com.me.obo.ballgame.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Log;

/**
 * Created by obo on 2017/10/28.
 * Email:obo1993@gmail.com
 */

public class GameManager {

    private static final String TAG = "GameManager";

    BallGroup ballGroup;
    BallGroup myBallGroup;
    BallGroup otherBallGroup ;
    Site site;
    private Matrix matrix = new Matrix();

    public GameManager() {
        site = new Site(1000, 1000);
        ballGroup = new BallGroup();
        myBallGroup = new BallGroup();
        PointF p = myBallGroup.balls.get(0).position;
        otherBallGroup = new BallGroup( new PointF(p.x + 10, p.y + 10), new PointF(1f, 1f), 5);
    }

    public void draw(Canvas canvas) {
        matrix.setTranslate(-myBallGroup.visibleRect.left, -myBallGroup.visibleRect.top);
        float left = - myBallGroup.visibleRect.left;
        float top = - myBallGroup.visibleRect.top;
        Log.i(TAG, "draw left = " + left + " top = " + top);
        float ratio = GameConfig.screenWidth / (myBallGroup.visibleRect.right - myBallGroup.visibleRect.left);
        matrix.postScale(ratio, ratio);
        canvas.setMatrix(matrix);
        canvas.drawColor(Color.BLACK);
        site.draw(canvas, myBallGroup.visibleRect);
        myBallGroup.draw(canvas, myBallGroup.visibleRect);
        otherBallGroup.draw(canvas, myBallGroup.visibleRect);
    }

    public void move(float timeDistance) {
//        otherBallGroup.move(timeDistance);
        myBallGroup.move(timeDistance);
    }

    public void setSpeed(PointF speed) {
        myBallGroup.setSpeed(speed);
    }
}
