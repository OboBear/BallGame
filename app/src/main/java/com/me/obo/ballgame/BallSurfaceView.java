package com.me.obo.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.me.obo.ballgame.game.GameManager;

/**
 * Created by obo on 2017/10/28.
 * Email:obo1993@gmail.com
 */

public class BallSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "BallSurfaceView";

    public SurfaceHolder mSurfaceHolder = null;
    private GameManager gameManager;
    private long lastMoveTime = 0;

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
        lastMoveTime = System.currentTimeMillis();
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gameManager.move((System.currentTimeMillis() - lastMoveTime)/10000f);

                    startFresh();
                }
            }
        }.start();
    }

    private void startFresh() {
        if (mSurfaceHolder != null) {
            Canvas canvas = mSurfaceHolder.lockCanvas();
            gameManager.draw(canvas);
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void setSpeed(PointF speed) {
        gameManager.setSpeed(speed);
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
}
