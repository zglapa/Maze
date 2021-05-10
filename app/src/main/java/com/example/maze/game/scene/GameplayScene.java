package com.example.maze.game.scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;

import com.example.maze.R;
import com.example.maze.game.Constants;
import com.example.maze.game.objects.Eater;
import com.example.maze.game.sensors.Orientation;
import com.example.maze.game.objects.Ball;
import com.example.maze.game.objects.LevelField;
import com.example.maze.game.objects.Wall;

import java.util.Random;

public class GameplayScene implements Scene {

    private final SceneManager sceneManager;
    private final Ball ball;
    private final Point ballPoint;
    private final LevelField levelField;
    private final Orientation orientation;
    private long frameTime;
    private final Paint paint;
    private Bitmap backgroundBitmap;
    private Rect rectangle;
    private final Point ballStartPosition;

    private boolean gameEnd;

    private final Context context;

    public GameplayScene(SceneManager sceneManager, Context context, int level){
        this.sceneManager = sceneManager;
        this.context = context;

        ball = new Ball(context, 54, 27, 5, 4);
        ballPoint = new Point((int)ball.getPositionX(), (int)ball.getPositionY());
        ballStartPosition = new Point((int)ball.getPositionX(), (int)ball.getPositionY());

        levelField = new LevelField(context.getResources(), context, level);

        orientation = new Orientation(context);
        orientation.register();
        frameTime = System.currentTimeMillis();

        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.grass_green, null));

//        backgroundBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.grass_simple);
//        rectangle = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    }

    @Override
    public void update() {
        if(!gameEnd){
            levelField.getEndPoint().update();
            updateBallPosition();
        }

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPaint(paint);
//        canvas.drawBitmap(backgroundBitmap,null, rectangle, paint);
        levelField.draw(canvas);
        ball.draw(canvas);

    }

    @Override
    public void terminate() {
        sceneManager.setScene(0);
    }

    public void updateBallPosition(){
        int x = ballPoint.x, y = ballPoint.y;

        int finalXmove = 0;
        int finalYmove = 0;
        long elapsedTime = System.currentTimeMillis() - frameTime;
        frameTime = System.currentTimeMillis();
        if(orientation.getOrientation() != null  && orientation.getStartOrientation() != null){
            float xx = orientation.getOrientation()[1] - orientation.getStartOrientation()[1];
            float yy = orientation.getOrientation()[2] - orientation.getStartOrientation()[2];


            float xSpeed = 5 * xx * Constants.SCREEN_WIDTH/2000f;
            float ySpeed = 5 * yy * Constants.SCREEN_HEIGHT/2000f;

            Random random = new Random();
            finalXmove = (int)(xSpeed*elapsedTime);
            finalYmove = (int)(ySpeed*elapsedTime);
            x = (int) (ballPoint.x - xSpeed*elapsedTime);
            y = (int) (ballPoint.y - ySpeed*elapsedTime);
        }

        if(x < 0) x = 0;
        if(x > Constants.SCREEN_WIDTH) x = Constants.SCREEN_WIDTH;
        if(y < 0) y = 0;
        if(y > Constants.SCREEN_HEIGHT) y = Constants.SCREEN_HEIGHT;

        Ball prevBall = new Ball(context, ball.getPositionX(), ball.getPositionY());

        ballPoint.set(x,y);

        ball.update(ballPoint);

        for(Wall wall : levelField.getWalls()){
            if(wall.intersects(ball, prevBall)){
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
        for(Eater eater: levelField.getEaters()){
            if(eater.intersects(ball)){
                ballPoint.set(ballStartPosition.x, ballStartPosition.y);
                ball.update(ballPoint);
            }
        }



        if(levelField.getEndPoint().intersects(ball)){
            gameEnd = true;
//            endGameNotif.setVisibility(View.VISIBLE);
        }

    }
    public boolean isGameEnd(){
        return gameEnd;
    }
}
