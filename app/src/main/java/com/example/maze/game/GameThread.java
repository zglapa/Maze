package com.example.maze.game;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.example.maze.R;
import com.example.maze.game.scene.SceneManager;

public class GameThread extends Thread {

    public static final int MAX_FPS = 30;
    private double averageFPS;
    private final TextureView textureView;
    private final SceneManager sceneManager;
    private boolean isRunning;
    public static Canvas canvas;

    public GameThread(TextureView textureView, SceneManager sceneManager){
        super();
        this.textureView = textureView;
        this.sceneManager = sceneManager;
    }

    @Override
    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(isRunning){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.textureView.lockCanvas();
                synchronized (textureView){
                    this.sceneManager.update();
                    this.sceneManager.draw(canvas);
                }

            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if(canvas != null){
                    try{
                        textureView.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;

            try{
                if(waitTime > 0){
                    sleep(waitTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if(frameCount == MAX_FPS){
                averageFPS = 1000f/(((double)totalTime/frameCount)/1000000f);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
            if(sceneManager.gameEnded()){
                break;
            }
        }

    }


    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
}
