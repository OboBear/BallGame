package com.me.obo.ballgame.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by obo on 2017/10/28.
 * Email:obo1993@gmail.com
 */

public class BallGroup {
    List<Ball> balls = new ArrayList<>();
    RectF visibleRect = new RectF();

    private Paint paint;

    public BallGroup() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        Ball ball = new Ball(new PointF((int) (Math.random() * 900) + 100, (int) (Math.random() * 900) + 100), new PointF(), 10);
        balls.add(ball);
        visibleRect = new RectF(ball.position.x - ball.radius * 4, ball.position.y - ball.radius * 4 / GameConfig.widthHeightRatio, ball.position.x + ball.radius * 4, ball.position.y + ball.radius * 4 / GameConfig.widthHeightRatio);
    }

    public BallGroup(PointF position, PointF speed, float radius) {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        Ball ball = new Ball(position, speed, radius);
        balls.add(ball);
        visibleRect = new RectF(ball.position.x - ball.radius * 4, ball.position.y - ball.radius * 4 / GameConfig.widthHeightRatio, ball.position.x + ball.radius * 4, ball.position.y + ball.radius * 4 / GameConfig.widthHeightRatio);
    }

    public void freshVisibleRect() {

    }

    public void move(float timeDistance) {
        for (Ball ball : balls) {
            ball.move(timeDistance);
            visibleRect.set(ball.position.x - ball.radius * 4, ball.position.y - ball.radius * 4 / GameConfig.widthHeightRatio, ball.position.x + ball.radius * 4, ball.position.y + ball.radius * 4 / GameConfig.widthHeightRatio);
        }
    }


    public void draw(Canvas canvas, RectF visibleRect) {
        for (Ball ball : balls) {
            if (visibleRect.left <= ball.visibleRect.right
                    && visibleRect.right >= ball.visibleRect.left
                    && visibleRect.top <= ball.visibleRect.bottom
                    && visibleRect.bottom >= ball.visibleRect.top) {  // 落在可视范围
                canvas.drawCircle(ball.position.x, ball.position.y, ball.radius, paint);
            }
        }
    }

    public void setSpeed(PointF speed) {
        this.balls.get(0).speed = speed;
    }

    public void split() {
    }

    public void send() {

    }
}
