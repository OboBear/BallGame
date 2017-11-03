package com.me.obo.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.me.obo.ballgame.game.GameConfig;
import com.me.obo.ballgame.game.GameManager;
import com.me.obo.ballgame.game.RickerManager;

/**
 * Created by obo on 2017/10/28.
 * Email:obo1993@gmail.com
 */

public class BallSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "BallSurfaceView";

    public SurfaceHolder mSurfaceHolder = null;
    private GameManager gameManager;
    private PointF position = new PointF();
    private PointF positionBack = new PointF();
    private RickerManager rickerManager;
    private boolean isRuning = true;
    PointF speed = new PointF();

    public BallSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "onMeasure");
    }

    public void init() {
        gameManager = new GameManager();

    }

    public void startGame() {
        startMove();
    }

    private void startMove() {
        rickerManager = new RickerManager(GameConfig.screenHeight / 8, GameConfig.screenHeight / 16);

        new Thread() {
            @Override
            public void run() {
                super.run();
                while (isRuning) {
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gameManager.move(1f);
                    startFresh();
                }
            }
        }.start();
    }

    private void startFresh() {
        if (mSurfaceHolder != null) {
            Canvas canvas = mSurfaceHolder.lockCanvas();
            gameManager.draw(canvas);
            // 绘制摇杆
            rickerManager.draw(canvas, positionBack, position);
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void stopGame() {
        isRuning = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                position.x = event.getX();
                position.y = event.getY();
                positionBack.x = event.getX();
                positionBack.y = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                position.x = event.getX();
                position.y = event.getY();
                float xDis = position.x - positionBack.x;
                float yDis = position.y - positionBack.y;
                double xDisPow = Math.pow(xDis, 2);
                double yDisPow = Math.pow(yDis, 2);
                double lengthPow = Math.pow(GameConfig.screenHeight / 8, 2);
                if (xDisPow + yDisPow > lengthPow) {
                    double rate = Math.sqrt((xDisPow + yDisPow)/lengthPow);
                    xDis /= rate;
                    yDis /= rate;
                    position.x = positionBack.x + xDis;
                    position.y = positionBack.y + yDis;
                }
                speed.x = xDis / (GameConfig.screenHeight / 6);
                speed.y = yDis / (GameConfig.screenHeight / 6);
                if (Math.abs(speed.x) < 0.1) {
                    speed.x = 0;
                }
                if (Math.abs(speed.y) < 0.1) {
                    speed.y = 0;
                }
                gameManager.setSpeed(speed);
                break;

            case MotionEvent.ACTION_UP:
                positionBack.x = getWidth() / 6;
                positionBack.y = getHeight() * 3/4;
                position.x = positionBack.x;
                position.y = positionBack.y;
                break;

            default:
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public void split() {
        gameManager.split();
    }

    public void send() {
        gameManager.send();
    }
}
