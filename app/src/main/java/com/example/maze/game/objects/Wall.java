package com.example.maze.game.objects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import androidx.core.content.ContextCompat;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.example.maze.R;
import com.example.maze.game.Constants;


public class Wall implements GameObject {

    private final int left, top, right, bottom;
    private final Paint paint;
    private final Rect realBody;
    private final Rectangle rectangle;
    private final Bitmap bitmap;

    public Wall(Resources resources, Context context, int left, int top, int right, int bottom) {
        Bitmap bitmap1;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.paint = new Paint();
        this.paint.setColor(ContextCompat.getColor(context, R.color.hedge_green));
        this.rectangle = new Rectangle(left, top, right - left, bottom - top);
        this.realBody = new Rect(left, top, right, bottom);
        if((realBody.height() > realBody.width() && realBody.height() <= Constants.SCREEN_HEIGHT/2) || (realBody.width() > realBody.height() && realBody.width() <=Constants.SCREEN_WIDTH/2)){
            bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.hedge_simple_mid);
        }
        else {
            bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.hedge_simple_long);
        }
        if(realBody.height() > realBody.width()){
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            bitmap1 =  Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), bitmap1.getHeight(), matrix, true);
        }
        bitmap = bitmap1;
    }
    public Wall(Context context, int widthDivider, int heightDivider, int left, int top, int right, int bottom){
        Bitmap bitmap1;
        this.left = left * Constants.SCREEN_WIDTH/widthDivider;
        this.top = top * Constants.SCREEN_HEIGHT/heightDivider;
        this.right = right * Constants.SCREEN_WIDTH/widthDivider;
        this.bottom = bottom * Constants.SCREEN_HEIGHT/heightDivider;
        this.paint = new Paint();
        this.paint.setColor(ContextCompat.getColor(context, R.color.hedge_green));
        this.rectangle = new Rectangle(this.left, this.top, this.right - this.left, this.bottom - this.top);
        this.realBody = new Rect(this.left, this.top, this.right, this.bottom);
        if((realBody.height() > realBody.width() && realBody.height() <= Constants.SCREEN_HEIGHT/2) || (realBody.width() > realBody.height() && realBody.width() <=Constants.SCREEN_WIDTH/2)){
            bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.hedge_simple_mid);
        }
        else {
            bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.hedge_simple_long);
        }
        if(realBody.height() > realBody.width()){
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            bitmap1 =  Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), bitmap1.getHeight(), matrix, true);
        }
        bitmap = bitmap1;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, realBody, paint);
//        canvas.drawRect(realBody, paint);
    }

    @Override
    public void update() {

    }

    public boolean intersects(Ball ball, Ball prevBall) {
//        if(Intersector.overlaps(ball.getBoundingBox(), this.rectangle)){
//            paint.setColor(Color.GREEN);
//        }
//        else paint.setColor(Color.RED);
//        return Intersector.overlaps(ball.getBoundingBox(), this.rectangle);
        float[] vertices;
        if(prevBall.getPositionX() > ball.getPositionX()){
            vertices = new float[]{(float)(ball.getPositionX() - ball.getRadius()), (float)(ball.getPositionY() - ball.getRadius()),
                    (float)(prevBall.getPositionX() + prevBall.getRadius()), (float)(prevBall.getPositionY() - prevBall.getRadius()),
                    (float)(prevBall.getPositionX() + prevBall.getRadius()), (float)(prevBall.getPositionY() + prevBall.getRadius()),
                    (float)(ball.getPositionX() - ball.getRadius()), (float)(ball.getPositionY() + ball.getRadius())};
        }else{
            vertices = new float[]{(float)(prevBall.getPositionX() - prevBall.getRadius()), (float)(prevBall.getPositionY() - prevBall.getRadius()),
                    (float)(ball.getPositionX() + ball.getRadius()), (float)(ball.getPositionY() - ball.getRadius()),
                    (float)(ball.getPositionX() + ball.getRadius()), (float)(ball.getPositionY() + ball.getRadius()),
                    (float)(prevBall.getPositionX() - prevBall.getRadius()), (float)(prevBall.getPositionY() + prevBall.getRadius())};
        }
        Polygon polygon = new Polygon(vertices);
        Polygon thisPolygon = new Polygon(new float[]{left, top, right, top, right, bottom, left, bottom});
        if(Intersector.overlapConvexPolygons(thisPolygon, polygon)){
            return true;
        }
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
