package com.example.maze.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import androidx.core.content.ContextCompat;

import com.example.maze.R;

public class Ball implements GameObject{
    private double positionX;
    private double positionY;
    private final double radius;
    private final Paint paint;

    public Ball(Context context, double radius){
        this.radius = radius;
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.red));
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawCircle((float)positionX, (float)positionY, (float)radius, paint);
    }

    @Override
    public void update(){

    }

    public void update(Point point){
        setPosition(point.x, point.y);
    }

    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    public double getPositionX(){
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setColor(){
        paint.setColor(Color.GREEN);
    }

    public double getRadius(){
        return this.radius;
    }
}
