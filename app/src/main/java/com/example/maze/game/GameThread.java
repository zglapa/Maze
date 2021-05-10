package com.example.maze.game;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.TextView;

import com.example.maze.R;

public class GameThread extends Thread {

    public static final int MAX_FPS = 30;
    private double averageFPS;
    private final SurfaceHolder surfaceHolder;
    private final GamePanel gamePanel;
    private boolean isRunning;
    public static Canvas canvas;

    public GameThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
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
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }

            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
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
            if(gamePanel.gameEnded()){
                synchronized (surfaceHolder){
                    ((Activity)gamePanel.getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            long time = gamePanel.getStartTime() - System.currentTimeMillis();

                            gamePanel.setVisibility(View.INVISIBLE);

                        }
                    });
                }
//                gamePanel.setVisibility(View.INVISIBLE);
                break;
            }
        }

    }


    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
}
