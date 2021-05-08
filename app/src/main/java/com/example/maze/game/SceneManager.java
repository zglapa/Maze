package com.example.maze.game;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    private int activeScene;

    public SceneManager(Context context, int level){
        activeScene = 0;
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
}
