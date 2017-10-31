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
        // 设置位移
        matrix.setTranslate(- myBallGroup.visibleRect.left, - myBallGroup.visibleRect.top);
        // 设置界面缩放比例
        float ratio = GameConfig.screenWidth / (myBallGroup.visibleRect.right - myBallGroup.visibleRect.left);
        matrix.postScale(ratio, ratio);
        // 设置Matrix
        canvas.setMatrix(matrix);
        // 设置背景颜色
        canvas.drawColor(Color.BLACK);
        // 绘制背景
        site.draw(canvas, myBallGroup.visibleRect);
        // 绘制小球
        myBallGroup.draw(canvas, myBallGroup.visibleRect);
        // 绘制摇杆
        otherBallGroup.draw(canvas, myBallGroup.visibleRect);
    }

    public void move(float timeDistance) {
//        otherBallGroup.move(timeDistance);
        myBallGroup.move(timeDistance);
    }

    public void setSpeed(PointF speed) {
        myBallGroup.setSpeed(speed);
    }

    public void split() {
        myBallGroup.split();
    }

    public void send() {
        myBallGroup.send();
    }
}
