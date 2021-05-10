package com.example.maze.game.scene;

import android.content.Context;
import android.graphics.Canvas;

import com.example.maze.game.scene.GameplayScene;
import com.example.maze.game.scene.Scene;

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

    public ArrayList<Scene> getScenes(){
        return scenes;
    }
}
