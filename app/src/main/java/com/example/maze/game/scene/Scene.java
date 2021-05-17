package com.example.maze.game.scene;

import android.graphics.Canvas;

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void drawBackground(Canvas canvas);
    public void terminate();
}
