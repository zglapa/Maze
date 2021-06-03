package com.example.maze.game;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.example.maze.R;
import com.example.maze.game.scene.SceneManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GameThread extends Thread {

    public static final int MAX_FPS = 30;
    private double averageFPS;
    private final TextureView textureView;
    private final SceneManager sceneManager;
    private boolean isRunning;
    public static Canvas canvas;
    private final Activity activity;

    public GameThread(TextureView textureView, SceneManager sceneManager, Activity activity){
        super();
        this.textureView = textureView;
        this.sceneManager = sceneManager;
        this.activity = activity;
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
                activity.runOnUiThread(()->{
                    activity.findViewById(R.id.endGame).setVisibility(View.VISIBLE);
                    TextView levelEndTextView = activity.findViewById(R.id.levelEnded);
                    TextView timeTextView = activity.findViewById(R.id.timeField);
                    String levelEnd = "Level " + sceneManager.getLevel() + " finished!";
                    levelEndTextView.setText(levelEnd);
                    long time = (System.currentTimeMillis() - sceneManager.getStartTime());
                    String hms = String.format(Locale.ENGLISH, "%02d:%02d:%03d", TimeUnit.MILLISECONDS.toMinutes(time),
                            TimeUnit.MILLISECONDS.toSeconds(time) % TimeUnit.MINUTES.toSeconds(1),
                            TimeUnit.MILLISECONDS.toMillis(time) % TimeUnit.SECONDS.toMillis(1));
                    timeTextView.setText(hms);
                });
                break;

            }
        }

    }


    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
}
