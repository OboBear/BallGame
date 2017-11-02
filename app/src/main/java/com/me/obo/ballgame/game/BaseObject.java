package com.me.obo.ballgame.game;

import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by obo on 2017/11/2.
 */

public class BaseObject {
    public PointF position;
    public PointF speed;
    public float radius;
    public float weight;

    public BaseObject() {

    }

    public BaseObject(PointF position, PointF speed, float weight) {
        this.position = position;
        this.speed = speed;
        this.radius = (float) Math.sqrt(weight);
        this.weight = weight;
    }

    /**
     * 设置重量
     * @param weight
     */
    public void setWeight(float weight) {
        this.radius = (float) Math.sqrt(weight);
        this.weight = weight;
    }

    public void move(float timeDistance) {
        if (speed.x != 0 || speed.y != 0) {
            position.x += timeDistance * speed.x;
            position.y += timeDistance * speed.y;
        }
    }
}
