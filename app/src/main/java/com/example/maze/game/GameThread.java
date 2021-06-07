package com.example.maze.game;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.example.maze.R;
import com.example.maze.game.database.AppDatabase;
import com.example.maze.game.scene.GameplayScene;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GameThread extends Thread {

    public static final int MAX_FPS = 30;
    private final TextureView textureView;
    private final GameplayScene gameplayScene;
    private boolean isRunning;
    public static Canvas canvas;
    private final Activity activity;
    public long levelTime;

    public GameThread(TextureView textureView, GameplayScene gameplayScene, Activity activity){
        super();
        this.textureView = textureView;
        this.gameplayScene = gameplayScene;
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
                    this.gameplayScene.update();
                    this.gameplayScene.draw(canvas);
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
                double averageFPS = 1000f / (((double) totalTime / frameCount) / 1000000f);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
            if(gameplayScene.isGameEnd()){
                activity.runOnUiThread(()->{
                    activity.findViewById(R.id.endGame).setVisibility(View.VISIBLE);
                    TextView levelEndTextView = activity.findViewById(R.id.levelEnded);
                    TextView timeTextView = activity.findViewById(R.id.timeField);
                    String levelEnd = "Level " + gameplayScene.getLevel() + " finished!";
                    levelEndTextView.setText(levelEnd);
                    if(gameplayScene.getLevel() == 12){
                        activity.findViewById(R.id.nextLevelButton).setVisibility(View.INVISIBLE);
                    }
                    long time = (System.currentTimeMillis() - gameplayScene.getStartTime());
                    levelTime = time;
                    manageScore(gameplayScene.getLevel(), time);
                    String hms = String.format(Locale.ENGLISH, "%02d:%02d:%03d", TimeUnit.MILLISECONDS.toMinutes(time),
                            TimeUnit.MILLISECONDS.toSeconds(time) % TimeUnit.MINUTES.toSeconds(1),
                            TimeUnit.MILLISECONDS.toMillis(time) % TimeUnit.SECONDS.toMillis(1));
                    timeTextView.setText(hms);
                });
                break;

            }
        }

    }
    private void manageScore(int level, long time){
        AppDatabase db = AppDatabase.getInstance(activity.getApplicationContext());
        if(db.playerDao().getTopPlayer(level) == null || db.playerDao().getTopPlayer(level).time > time){
            activity.findViewById(R.id.highscoreLayout).setVisibility(View.VISIBLE);
        }
    }
    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
}
