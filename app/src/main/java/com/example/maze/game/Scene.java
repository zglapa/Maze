package com.example.maze.game;

import android.graphics.Canvas;

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
}
