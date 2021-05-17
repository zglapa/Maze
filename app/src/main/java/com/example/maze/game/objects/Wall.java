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
        int resourceID;
        switch (Math.max(right - left, bottom - top)){
            case 4:
            case 5:
                resourceID = R.drawable.hedge4;
                break;
            case 6:
            case 7:
                resourceID = R.drawable.hedge6;
                break;
            case 8:
            case 9:
                resourceID = R.drawable.hedge8;
                break;
            case 10:
            case 11:
                resourceID = R.drawable.hedge10;
                break;
            case 12:
            case 13:
                resourceID = R.drawable.hedge12;
                break;
            case 14:
            case 15:
                resourceID = R.drawable.hedge14;
                break;
            case 16:
            case 17:
                resourceID = R.drawable.hedge16;
                break;
            case 18:
            case 19:
                resourceID = R.drawable.hedge18;
                break;
            case 20:
            case 21:
                resourceID = R.drawable.hedge20;
                break;
            case 22:
            case 23:
                resourceID = R.drawable.hedge22;
                break;
            case 54:
                resourceID = R.drawable.hedge54;
                break;
            default:
                resourceID = R.drawable.hedge27;
        }
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), resourceID);
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
