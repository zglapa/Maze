package com.example.maze;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.maze.game.Constants;
import com.example.maze.game.GamePanel;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constants.SCREEN_HEIGHT = displayMetrics.heightPixels;
        Constants.SCREEN_WIDTH = displayMetrics.widthPixels;
        Constants.WALL_SIZE = Constants.SCREEN_HEIGHT/15;

        setContentView(new GamePanel(this));
    }
}