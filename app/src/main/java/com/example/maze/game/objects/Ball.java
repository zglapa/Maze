package com.example.maze.game.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.example.maze.R;
import com.example.maze.game.Constants;

public class Ball implements GameObject {
    private double positionX;
    private double positionY;
    private final double radius;
    private final Paint paint;
    private final Rect realBody;
    private final Circle circle;
    private final Rectangle rectangle;
    private final Bitmap bitmap;

    public Ball(Context context, int widthDivider, int heightDivider, Point ballStartPoint){
        this.radius = Constants.BALL_RADIUS;
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.red));
        this.positionX = ballStartPoint.x * (double)Constants.SCREEN_WIDTH/widthDivider;
        this.positionY = ballStartPoint.y * (double)Constants.SCREEN_HEIGHT/heightDivider;
        this.realBody = new Rect((int)(this.positionX-radius*1.15), (int)(this.positionY-radius*1.15), (int)(this.positionX+radius*1.15), (int)(this.positionY+radius*1.15));
        this.circle = new Circle((float)positionX, (float)positionY, (float)radius);
        this.rectangle = new Rectangle((float)(positionX-radius), (float)(positionY-radius), (float)(2*radius), (float)(2*radius));
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
    }
    public Ball(Context context, double positionX, double positionY){
        this.radius = Constants.BALL_RADIUS;
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.red));
        this.positionX =  positionX;
        this.positionY = positionY;
        this.realBody = new Rect((int)(this.positionX-radius*1.15), (int)(this.positionY-radius*1.15), (int)(this.positionX+radius*1.15), (int)(this.positionY+radius*1.15));
        this.circle = new Circle((float)positionX, (float)positionY, (float)radius);
        this.rectangle = new Rectangle((float)(this.positionX-radius), (float)(this.positionY-radius), (float)(2*radius), (float)(2*radius));
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);

    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, null, realBody, paint);
//        canvas.drawCircle((float)positionX, (float)positionY, (float)radius, paint);
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
        realBody.set((int)(positionX - radius*1.15), (int)(positionY - radius*1.15), (int)(positionX + radius*1.15), (int)(positionY + radius*1.15));
        this.circle.setPosition((float)x, (float)y);
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

    public Circle getCircle(){return circle;}

    public Rectangle getBoundingBox(){return rectangle;}
}
