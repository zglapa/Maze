package com.example.maze.game.objects;

import android.graphics.Canvas;

public interface GameObject {
    public void draw(Canvas canvas);
    public void update();
}
