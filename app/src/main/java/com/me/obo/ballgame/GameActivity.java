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
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        GameConfig.screenWidth = wm.getDefaultDisplay().getWidth();
        GameConfig.screenHeight = wm.getDefaultDisplay().getHeight();
        GameConfig.widthHeightRatio = GameConfig.screenWidth * 1f / GameConfig.screenHeight;

        ballSurfaceView = (BallSurfaceView) findViewById(R.id.game_view);
        ballSurfaceView.startGame();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.split:
                ballSurfaceView.split();
                break;
            case R.id.send:
                ballSurfaceView.send();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ballSurfaceView.stopGame();
    }
}
