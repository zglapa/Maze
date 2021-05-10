package com.example.maze;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.example.maze.game.Constants;
import com.example.maze.game.GamePanel;

public class GameActivity extends Activity {
    private GamePanel gamePanel;
    private int level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constants.SCREEN_HEIGHT = displayMetrics.heightPixels;
        Constants.SCREEN_WIDTH = displayMetrics.widthPixels;
        Constants.WALL_SIZE = Constants.SCREEN_HEIGHT/18;
        Constants.ENDPT_RADIUS = 60;
        Constants.BALL_RADIUS = 45;
        Constants.EATER_RADIUS = 45;

        level = getIntent().getIntExtra("LEVEL", 0);
        setContentView(R.layout.activity_game);

        LayoutInflater layoutInflater = getLayoutInflater();
        ViewGroup parent = findViewById(R.id.gameLayout);
        if(parent != null){
            View view = layoutInflater.inflate(R.layout.activity_game, parent);
            gamePanel = new GamePanel(this, level);
            parent.addView(gamePanel);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void backButtonClicked(View view){
        Intent intent = new Intent(this, LevelChoiceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void nextLevelButtonClicked(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("LEVEL", level + 1);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}