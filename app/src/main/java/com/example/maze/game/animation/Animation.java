package com.example.maze.game.animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Animation {
    private final Bitmap[] frames;
    private int frameIndex;
    private final float frameTime;
    private boolean isPlaying = false;

    private long lastFrame;

    public Animation(Bitmap[] frames, float animationTime){
        this.frames = frames;
        frameIndex = 0;

        frameTime = animationTime/frames.length;
        lastFrame = System.currentTimeMillis();
    }

    public void update(){

        if(!isPlaying()) return;

        if(System.currentTimeMillis() - lastFrame > frameTime * 1000){
            frameIndex++;
            frameIndex = frameIndex % frames.length;
            lastFrame = System.currentTimeMillis();
        }
    }

    public void draw(Canvas canvas, Rect destination){
        if(!isPlaying()) return;

        canvas.drawBitmap(frames[frameIndex], null, destination, new Paint());
    }

    public boolean isPlaying(){
        return isPlaying;
    }
    public void play(){
        isPlaying = true;
        frameIndex = 0;
        lastFrame = System.currentTimeMillis();
    }
    public void stop(){
        isPlaying = false;
    }

}
