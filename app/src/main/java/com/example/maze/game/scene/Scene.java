package com.example.maze.game.scene;

import android.graphics.Canvas;

public interface Scene {
    void update();
    void draw(Canvas canvas);
    void drawBackground(Canvas canvas);
}
