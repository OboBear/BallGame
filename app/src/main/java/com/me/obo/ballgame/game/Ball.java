package com.me.obo.ballgame.game;

import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by obo on 2017/10/28.
 * Email:obo1993@gmail.com
 */

public class Ball extends BaseObject {

    public RectF visibleRect;

    public Ball(PointF position, PointF speed, int weight) {
        super(position, speed, weight);
        visibleRect = new RectF(position.x - radius, position.y - radius, position.x + radius, position.y + radius);
    }

    /**
     * 设置重量
     * @param weight
     */
    public void setWeight(int weight) {
        super.setWeight(weight);
        visibleRect.set(position.x - radius, position.y - radius, position.x + radius, position.y + radius);
    }

    /**
     * 小球移动
     * @param timeDistance
     */
    public void move(float timeDistance) {
        if (speed.x != 0 || speed.y != 0) {
            super.move(timeDistance);
            visibleRect.set(position.x - radius, position.y - radius, position.x + radius, position.y + radius);
        }
    }
}
