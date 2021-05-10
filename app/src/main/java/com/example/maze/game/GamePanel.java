package com.example.maze.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.maze.game.scene.GameplayScene;
import com.example.maze.game.scene.SceneManager;


@SuppressLint("ViewConstructor")
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private final SceneManager sceneManager;

    private GameThread thread;
    private final long startTime;

    public GamePanel(Context context, int level){
        super(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

        this.sceneManager = new SceneManager(context, level);

        startTime = System.currentTimeMillis();

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread = new GameThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
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

    public void update(){
        this.sceneManager.update();
    }


    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        this.sceneManager.draw(canvas);
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}
    public boolean isInEditMode (){
        return true;
    }
    public boolean gameEnded(){
        GameplayScene gameplayScene = (GameplayScene) sceneManager.getScenes().get(0);
        return gameplayScene.isGameEnd();
    }
    public long getStartTime(){
        return startTime;
    }
}
