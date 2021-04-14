package com.example.maze.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {

    private static final double MAX_UPS = 30f;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
    private GamePanel gamePanel;
    private final SurfaceHolder surfaceHolder;
    private boolean isRunning = false;
    private double averageUPS, averageFPS;


    public GameLoop(GamePanel gamePanel, SurfaceHolder surfaceHolder) {
        this.gamePanel = gamePanel;
        this.surfaceHolder = surfaceHolder;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run(){
        super.run();

        Canvas canvas = null;

        int updateCount = 0;
        int frameCount = 0;

        long startTime, elapsedTime, sleepTime;

        startTime = System.currentTimeMillis();
        while(isRunning){

            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    gamePanel.update();
                      gamePanel.draw(canvas);
                    updateCount++;

                }

            }catch(IllegalArgumentException e){
                e.printStackTrace();
            }
            finally {
                if(canvas != null) {
                    frameCount++;
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }


            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
            if(sleepTime > 0 && updateCount < MAX_UPS-1){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= 1000){
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFPS = frameCount /(1E-3 * elapsedTime);
                frameCount = 0;
                updateCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }
}
