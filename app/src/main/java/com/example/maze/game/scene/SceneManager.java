package com.example.maze.game.scene;

import android.content.Context;
import android.graphics.Canvas;

import com.example.maze.game.scene.GameplayScene;
import com.example.maze.game.scene.Scene;

import java.util.ArrayList;

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    private int activeScene;
    private final int level;

    public SceneManager(Context context, int level){
        activeScene = 0;
        this.level = level;
        scenes.add(new GameplayScene(this, context, level));
    }

    public void setScene(int activeScene){
        this.activeScene = activeScene;
    }

    public void update(){
        scenes.get(activeScene).update();
    }

    public void draw(Canvas canvas){
        scenes.get(activeScene).draw(canvas);
    }
    public void drawBackground(Canvas canvas){scenes.get(activeScene).drawBackground(canvas);}
    public boolean gameEnded(){
        return ((GameplayScene)scenes.get(activeScene)).isGameEnd();
    }
    public int getLevel(){return level;}
    public long getStartTime(){return ((GameplayScene)scenes.get(activeScene)).getStartTime(); }
    public ArrayList<Scene> getScenes(){
        return scenes;
    }
}
