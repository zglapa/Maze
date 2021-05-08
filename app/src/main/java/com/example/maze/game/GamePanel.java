package com.example.maze.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;


@SuppressLint("ViewConstructor")
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private SceneManager sceneManager;

    private GameThread thread;


    public GamePanel(Context context, int level, View endGameNotif){
        super(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

        this.sceneManager = new SceneManager(context, level, endGameNotif);

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
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            }catch (Exception e){
                e.printStackTrace();
            }
            retry = false;
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
}
