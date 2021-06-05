package com.example.maze.game.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;

import com.example.maze.game.Constants;
import com.example.maze.game.objects.Eater;
import com.example.maze.game.objects.LevelFactory;
import com.example.maze.game.sensors.Orientation;
import com.example.maze.game.objects.Ball;
import com.example.maze.game.objects.LevelField;
import com.example.maze.game.objects.Wall;


public class GameplayScene implements Scene {

    private final Ball ball;
    private final Point ballPoint;
    private final LevelField levelField;
    private final Orientation orientation;
    private long frameTime;
    private final Point ballStartPosition;
    private long startTime = 0;
    private boolean gameEnd;
    private final int level;
    private final Context context;

    public GameplayScene(Context context, int level){
        this.context = context;
        this.level = level;
        levelField = createLevelField(context, level);

        ballPoint = new Point(levelField.getBallStartPoint());
        ball = new Ball(context, 54, 27, ballPoint);

        int ballStartX = (int) (ballPoint.x * Constants.SCREEN_WIDTH/54f);
        int ballStartY = (int) (ballPoint.y * Constants.SCREEN_HEIGHT/27f);
        ballStartPosition = new Point(ballStartX, ballStartY);

        orientation = new Orientation(context);
        orientation.register();
        frameTime = System.currentTimeMillis();

    }

    @Override
    public void update() {
        for(Eater eater: levelField.getEaters()){
            eater.update();
        }
        if(!gameEnd){
            levelField.getEndPoint().update();
            updateBallPosition();
        }

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for(Eater eater: levelField.getEaters()){
            eater.draw(canvas);
        }
        levelField.getEndPoint().draw(canvas);
        ball.draw(canvas);

    }

    @Override
    public void drawBackground(Canvas canvas) {
        levelField.draw(canvas);
    }

    public void updateBallPosition(){
        int x = ballPoint.x, y = ballPoint.y;

        long elapsedTime = System.currentTimeMillis() - frameTime;
        frameTime = System.currentTimeMillis();
        if(orientation.getOrientation() != null  && orientation.getStartOrientation() != null){
            float xx = orientation.getOrientation()[1] - orientation.getStartOrientation()[1];
            float yy = orientation.getOrientation()[2] - orientation.getStartOrientation()[2];


            float xSpeed = 5 * xx * Constants.SCREEN_WIDTH/2000f;
            float ySpeed = 5 * yy * Constants.SCREEN_HEIGHT/2000f;

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


        if(startTime == 0 && (prevBall.getPositionX() != ball.getPositionX() || prevBall.getPositionY() != ball.getPositionY())){
            startTime = System.currentTimeMillis();
        }
        if(levelField.getEndPoint().intersects(ball)){
            gameEnd = true;
        }

    }
    public boolean isGameEnd(){
        return gameEnd;
    }
    public long getStartTime(){return startTime;}
    private LevelField createLevelField(Context context, int level){
        LevelField levelFieldTemp;
        switch(level) {
            case 2:
                levelFieldTemp = LevelFactory.produceLevel2(context);
                break;
            case 3:
                levelFieldTemp = LevelFactory.produceLevel3(context);
                break;
            case 4:
                levelFieldTemp = LevelFactory.produceLevel4(context);
                break;
            case 5:
                levelFieldTemp = LevelFactory.produceLevel5(context);
                break;
            case 6:
                levelFieldTemp = LevelFactory.produceLevel6(context);
                break;
            case 7:
                levelFieldTemp = LevelFactory.produceLevel7(context);
                break;
            case 8:
                levelFieldTemp = LevelFactory.produceLevel8(context);
                break;
            default:
                levelFieldTemp = LevelFactory.produceLevel1(context);
        }
        return levelFieldTemp;
    }

    public int getLevel() {
        return level;
    }
}
