package com.example.maze;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.maze.game.Constants;
import com.example.maze.game.GameThread;
import com.example.maze.game.database.AppDatabase;
import com.example.maze.game.database.Player;
import com.example.maze.game.database.PlayerAdapter;
import com.example.maze.game.scene.SceneManager;

public class GameActivity extends Activity implements TextureView.SurfaceTextureListener {
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
        Constants.ENDPT_RADIUS = 80;
        Constants.BALL_RADIUS = 40;
        Constants.EATER_RADIUS = 70;

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
        super.onDestroy();
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
    }

    @Override
    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
        thread = new GameThread(drawView, sceneManager, this);
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
    private void unlockNewLevel(int level){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int topLevel = sharedPreferences.getInt("TOP_LEVEL", 0);
        if(level > topLevel){
            editor.remove("TOP_LEVEL");
            editor.putInt("TOP_LEVEL", level);
        }
        editor.apply();
    }
    public void highscoreButtonClicked(View view){
        findViewById(R.id.playerInputLayout).setVisibility(View.VISIBLE);
    }

    public void saveButtonClicked(View view){
        EditText playerNameEditText = findViewById(R.id.playerNameEditText);
        String playerName = playerNameEditText.getText().toString();
        if(playerName.length() < 1) return;
        long time = thread.levelTime;
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        Player player = new Player();
        player.name = playerName;
        player.time = time;
        player.level = level;
        db.playerDao().insertPlayers(player);
        findViewById(R.id.playerInputLayout).setVisibility(View.INVISIBLE);
        findViewById(R.id.addHighscoreButton).setEnabled(false);

    }

    public void menuButtonClicked(View view){
        unlockNewLevel(level + 1);
        Intent intent = new Intent(this, LevelChoiceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void nextLevelButtonClicked(View view){
        unlockNewLevel(level + 1);
        Intent intent = new Intent(this, GameActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("LEVEL", level + 1);
        startActivity(intent);
    }

}