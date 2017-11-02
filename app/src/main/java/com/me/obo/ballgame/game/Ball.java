package com.me.obo.ballgame.game;

import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by obo on 2017/10/28.
 * Email:obo1993@gmail.com
 */

public class Ball {
    public PointF position;
    public PointF speed;
    public float radius;
    public float weight;
    public RectF visibleRect;

    public Ball(PointF position, PointF speed, float weight) {
        this.position = position;
        this.speed = speed;
        this.radius = (float) Math.sqrt(weight);
        this.weight = weight;
        visibleRect = new RectF(position.x - radius, position.y - radius, position.x + radius, position.y + radius);
    }

    /**
     * 设置重量
     * @param weight
     */
    public void setWeight(float weight) {
        this.radius = (float) Math.sqrt(weight);
        this.weight = weight;
        visibleRect.set(position.x - radius, position.y - radius, position.x + radius, position.y + radius);
    }

    public void move(float timeDistance) {
        if (speed.x != 0 || speed.y != 0) {
            position.x += timeDistance * speed.x;
            position.y += timeDistance * speed.y;
            visibleRect.set(position.x - radius, position.y - radius, position.x + radius, position.y + radius);
        }
    }
}
