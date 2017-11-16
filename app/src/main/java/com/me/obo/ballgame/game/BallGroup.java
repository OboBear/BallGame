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
 * 小球集合，可以用于表示一个用户的所有小球
 * Created by obo on 2017/10/28.
 * Email:obo1993@gmail.com
 */

public class BallGroup {
    public List<Ball> balls = new ArrayList<>();
    public RectF visibleRect = new RectF();
    private PointF accelerate;
    private PointF gravityCenter = new PointF();
    private int weight;
    private Paint paint;


    public BallGroup() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        Ball ball = new Ball(new PointF((int) (Math.random() * 900) + 100, (int) (Math.random() * 900) + 100), new PointF(), 100);
        balls.add(ball);
        weight = ball.weight;
        resetVisibleRect();
        resetGravityCenter();
    }

    public BallGroup(PointF position, PointF speed, int radius) {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        Ball ball = new Ball(position, speed, radius);
        balls.add(ball);
        resetVisibleRect();
        resetGravityCenter();
    }

    public void move(float timeDistance) {
        for (Ball ball : balls) {
            ball.move(timeDistance);
        }
        resetVisibleRect();
        resetGravityCenter();
    }

    private void resetVisibleRect() {
        visibleRect.set(balls.get(0).visibleRect);
        for (Ball ball : balls) {
            visibleRect.union(ball.visibleRect);
        }
        visibleRect.left -= Math.sqrt(weight) + 20;
        visibleRect.right += Math.sqrt(weight) + 20;
        visibleRect.top -= Math.sqrt(weight) + 20;
        visibleRect.bottom += Math.sqrt(weight) + 20;

        float needWidth = visibleRect.height() * GameConfig.widthHeightRatio;
        if (visibleRect.right - visibleRect.left > needWidth) {
            float needHeight = visibleRect.width() / GameConfig.widthHeightRatio;
            visibleRect.top = visibleRect.centerY() - needHeight/2;
            visibleRect.bottom = visibleRect.centerY() + needHeight/2;
        } else {
            visibleRect.left = visibleRect.centerX() - needWidth/2;
            visibleRect.right = visibleRect.centerX() + needWidth/2;
        }
    }

    private void resetGravityCenter() {
        float x = 0;
        float y = 0;
        for (Ball ball : balls) {
            x += ball.weight * ball.position.x;
            y += ball.weight * ball.position.y;
        }
        gravityCenter.set(x/balls.size(), y/balls.size());
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
        for(Ball ball: this.balls) {
            ball.speed = speed;
        }
    }

    public void split() {

    }

    public void send() {

    }

    public void setAccelerate(PointF accelerate) {
        this.accelerate = accelerate;
    }
}
