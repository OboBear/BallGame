package com.me.obo.ballgame.game;

import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by obo on 2017/10/28.
 * Email:obo1993@gmail.com
 */

public class Ball extends BaseObject {

    public RectF visibleRect;
    public final float MAX_ACCELERATE = 1;

    public Ball(PointF position, PointF speed, int weight) {
        super(position, speed, weight);
        visibleRect = new RectF(position.x - radius, position.y - radius, position.x + radius, position.y + radius);
        this.maxSpeed = MAX_SPEED/radius;
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
        if (!speed.equals(expectSpeed)) {
            float distSpeedX = expectSpeed.x - speed.x;
            float distSpeedY = expectSpeed.y - speed.y;
            double distSpeedXPow = Math.pow(distSpeedX, 2);
            double distSpeedYPow =  Math.pow(distSpeedY, 2);
            double maxAcceleratePow = Math.pow(MAX_ACCELERATE, 2);
            if(distSpeedXPow + distSpeedYPow > maxAcceleratePow) {
                double rate = Math.sqrt(maxAcceleratePow/(distSpeedXPow + distSpeedYPow));
                speed.set((float) (speed.x + distSpeedX * rate), (float) (speed.y + distSpeedY * rate));
            } else {
                speed.set(expectSpeed.x, expectSpeed.y);
            }
        }
        if (speed.x != 0 || speed.y != 0) {
            super.move(timeDistance);
            visibleRect.set(position.x - radius, position.y - radius, position.x + radius, position.y + radius);
        }
    }

    public void setExpectSpeedWithSpeedCenter(PointF speedCenter) {
        this.expectSpeed.set(speedCenter.x - position.x, speedCenter.y - position.y);
    }
}
