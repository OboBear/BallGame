package com.me.obo.ballgame;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.me.obo.ballgame.game.GameConfig;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    BallSurfaceView ballSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        GameConfig.screenWidth = wm.getDefaultDisplay().getWidth();
        GameConfig.screenHeight = wm.getDefaultDisplay().getHeight();

        ballSurfaceView = (BallSurfaceView) findViewById(R.id.game_view);
        ballSurfaceView.startGame();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top:
                ballSurfaceView.setSpeed(new PointF(0, -1));
                break;
            case R.id.left:
                ballSurfaceView.setSpeed(new PointF(-1, 0));
                break;
            case R.id.right:
                ballSurfaceView.setSpeed(new PointF(1, 0));
                break;
            case R.id.bottom:
                ballSurfaceView.setSpeed(new PointF(0, 1));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ballSurfaceView.stopGame();
    }
}
