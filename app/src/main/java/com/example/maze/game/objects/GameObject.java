package com.example.maze.game.objects;

import android.graphics.Canvas;

public interface GameObject {
    void draw(Canvas canvas);
    void update();
}
