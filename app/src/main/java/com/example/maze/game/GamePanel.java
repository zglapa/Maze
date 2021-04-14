package com.example.maze.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private final Ball ball;
    private Point ballPoint;
    private final LevelField levelField;
    private Orientation orientation;
    private long frameTime;

    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

        ball = new Ball(getContext(), 30);
        ballPoint = new Point(150, 150);

        levelField = new LevelField(context);

        orientation = new Orientation(getContext());
        orientation.register();
        frameTime = System.currentTimeMillis();
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread = new GameThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            }catch (Exception e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update(){

        int x = ballPoint.x, y = ballPoint.y;

        long elapsedTime = System.currentTimeMillis() - frameTime;
        frameTime = System.currentTimeMillis();
        if(orientation.getOrientation() != null  && orientation.getStartOrientation() != null){
            float xx = orientation.getOrientation()[1] - orientation.getStartOrientation()[1];
            float yy = orientation.getOrientation()[2] - orientation.getStartOrientation()[2];


            float xSpeed = 3 * xx * Constants.SCREEN_WIDTH/2000f;
            float ySpeed = 3 * yy * Constants.SCREEN_HEIGHT/2000f;

//            System.out.println("x " + xSpeed + " | y" + ySpeed);


            x = (int) (ballPoint.x - (Math.abs(xSpeed*elapsedTime) > 3 ? xSpeed*elapsedTime : 0));
            y = (int) (ballPoint.y - (Math.abs(ySpeed*elapsedTime) > 3 ? ySpeed*elapsedTime : 0));
        }

        if(x < 0) x = 0;
        if(x > Constants.SCREEN_WIDTH) x = Constants.SCREEN_WIDTH;
        if(y < 0) y = 0;
        if(y > Constants.SCREEN_HEIGHT) y = Constants.SCREEN_HEIGHT;



        Ball prevBall = new Ball(getContext(), ball.getRadius());
        prevBall.setPosition(ball.getPositionX(), ball.getPositionY());

        ballPoint.set(x,y);

        ball.update(ballPoint);

        for(Wall wall : levelField.getWalls()){
            if(wall.intersects(ball)){

                if(ball.getPositionY() - prevBall.getPositionY() > 0 && !wall.intersectsTop(prevBall)){
                    y = (int)(wall.getTop() - ball.getRadius()) - 1;
                }
                if(ball.getPositionY() - prevBall.getPositionY() < 0 && !wall.intersectsBottom(prevBall)){
                    y = (int)(wall.getBottom() + ball.getRadius()) + 1;
                }

                if(ball.getPositionX() - prevBall.getPositionX() > 0 && !wall.intersectsLeft(prevBall)){
                    x = (int)(wall.getLeft() - ball.getRadius()) - 1;
                }
                if(ball.getPositionX() - prevBall.getPositionX() < 0 && !wall.intersectsRight(prevBall)){
                    x = (int)(wall.getRight() + ball.getRadius()) + 1;
                }

                ballPoint.set(x,y);
                ball.update(ballPoint);
            }
        }


    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        levelField.draw(canvas);
        ball.draw(canvas);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        switch(event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
//                ballPoint.set((int)event.getX(), (int)event.getY());
//                return true;
//        }
//        return super.onTouchEvent(event);
//    }

}
