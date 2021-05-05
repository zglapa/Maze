package com.example.maze.game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;

import androidx.core.content.ContextCompat;

import com.example.maze.R;


public class Wall implements GameObject{

    private final int left, top, right, bottom;
    private final Paint paint;
    private final Rect rectangle;
    private final Bitmap bitmap;
    public Wall(Resources resources, Context context, int left, int top, int right, int bottom){
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.paint = new Paint();
//        this.paint.setColor(ContextCompat.getColor(context, R.color.hedge_green));
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.hedge_back);
        paint.setFilterBitmap(true);
        BitmapShader fillBMPshader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(fillBMPshader);
        this.rectangle = new Rect(left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rectangle, this.paint);
    }

    @Override
    public void update() {

    }

    public boolean intersects(Ball ball) {
        if(!intersectsLeft(ball) || !intersectsRight(ball) || !intersectsTop(ball) || !intersectsBottom(ball)) {
//            paint.setColor(Color.GREEN);
            return false;
        }
//        paint.setColor(Color.RED);
        return true;
    }
    public boolean intersectsLeft(Ball ball){
        return ball.getPositionX() + ball.getRadius() >= left;
    }
    public boolean intersectsTop(Ball ball){
        return ball.getPositionY() + ball.getRadius() >= top;
    }
    public boolean intersectsRight(Ball ball){
        return ball.getPositionX() - ball.getRadius() <= right;
    }
    public boolean intersectsBottom(Ball ball){
        return ball.getPositionY() - ball.getRadius() <= bottom;
    }

    public double getLeft(){return left;}
    public double getTop(){return top;}
    public double getRight(){return right;}
    public double getBottom(){return bottom;}

}
