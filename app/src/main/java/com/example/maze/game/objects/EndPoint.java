package com.example.maze.game.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.example.maze.R;
import com.example.maze.game.Constants;
import com.example.maze.game.animation.Animation;
import com.example.maze.game.animation.AnimationManager;

public class EndPoint implements GameObject {

    private final double positionX;
    private final double positionY;
    private final double radius;
    private final Paint paint;
    private final Rect realBody;
    private final Circle circle;
    private final Bitmap bitmap;

    public EndPoint(Context context, int widthDivider, int heightDivider, int x, int y){
        this.radius = Constants.ENDPT_RADIUS;
        this.paint = new Paint();
        this.paint.setColor(context.getColor(R.color.gray));
        this.positionX =  x * (double)Constants.SCREEN_WIDTH/widthDivider;
        this.positionY = y * (double)Constants.SCREEN_HEIGHT/heightDivider;
        this.realBody = new Rect((int)(this.positionX-radius), (int)(this.positionY-radius), (int)(this.positionX+radius), (int)(this.positionY+radius));
        this.circle = new Circle((float)this.positionX, (float)this.positionY, (float)radius*0.2f);
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.basket);

    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, null, realBody, paint);
    }

    @Override
    public void update(){
    }

    public boolean intersects(Ball ball){
        return Intersector.overlaps(ball.getCircle(), this.circle);
    }
    public double getPositionX(){
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getRadius(){
        return this.radius;
    }

    public Rect getRealBody(){return realBody;};
}
