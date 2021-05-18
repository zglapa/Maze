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

public class Eater implements GameObject{

    private final double positionX;
    private final double positionY;
    private final double radius;
    private final Rect realBody;
    private final Circle circle;
    private final AnimationManager animationManager;

    public Eater(Context context, int widthDivider, int heightDivider, int x, int y){
        this.radius = Constants.EATER_RADIUS;
        Paint paint = new Paint();
        paint.setColor(context.getColor(R.color.eater_blue));
        this.positionX =  x * (double)Constants.SCREEN_WIDTH/widthDivider;
        this.positionY = y * (double)Constants.SCREEN_HEIGHT/heightDivider;
        this.realBody = new Rect((int)(this.positionX-radius), (int)(this.positionY-radius), (int)(this.positionX+radius), (int)(this.positionY+radius));
        this.circle = new Circle((float)this.positionX, (float)this.positionY, (float)radius*0.6f);

        Bitmap idleImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.eater1);
        Bitmap spin1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.eater1);
        Bitmap spin2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.eater2);
        Bitmap spin3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.eater3);

        Animation idle = new Animation(new Bitmap[]{idleImage}, 2);
        Animation spin = new Animation(new Bitmap[]{spin1, spin2, spin3}, 0.5f);

        this.animationManager = new AnimationManager(new Animation[]{idle, spin});
    }
    @Override
    public void draw(Canvas canvas){
//        canvas.drawCircle((float)positionX, (float)positionY, (float)radius, paint);
        animationManager.draw(canvas, realBody);
    }

    @Override
    public void update(){
        animationManager.playAnimations(1);
        animationManager.update();
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
