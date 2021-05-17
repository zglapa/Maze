package com.example.maze;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.badlogic.gdx.graphics.Texture;
import com.example.maze.game.Constants;
import com.example.maze.game.GamePanel;
import com.example.maze.game.GameThread;
import com.example.maze.game.scene.SceneManager;

public class GameActivity extends Activity implements TextureView.SurfaceTextureListener {
    private GamePanel gamePanel;
    private TextureView backgroundView;
    private TextureView drawView;
    private GameThread thread;
    private SceneManager sceneManager;
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
        Constants.BALL_RADIUS = 40;
        Constants.EATER_RADIUS = 45;

        level = getIntent().getIntExtra("LEVEL", 0);


        this.sceneManager = new SceneManager(this, level);
        setContentView(R.layout.activity_game);

        backgroundView = findViewById(R.id.backgroundView);
        backgroundView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                Canvas canvas = backgroundView.lockCanvas();
                sceneManager.drawBackground(canvas);
                backgroundView.unlockCanvasAndPost(canvas);
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

            }
        });
        backgroundView.setOpaque(false);

        drawView = findViewById(R.id.drawView);
        drawView.setSurfaceTextureListener(this);
        drawView.setOpaque(false);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();while(true){
            try{
                System.out.println("trying");
                thread.setRunning(false);
                thread.join();
                System.out.println("Thread joined");
                break;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
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

    @Override
    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
        thread = new GameThread(drawView, sceneManager);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
        while(true){
            try{
                System.out.println("trying");
                thread.setRunning(false);
                thread.join();
                System.out.println("Thread joined");
                break;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

    }

}