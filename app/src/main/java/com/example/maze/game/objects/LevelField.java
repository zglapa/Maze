package com.example.maze.game.objects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;

import com.example.maze.game.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LevelField implements GameObject, Serializable {

    private final List<Wall> outerWalls;
    private final List<Wall> innerWalls;
    private final EndPoint endPoint;

    public LevelField(Resources resources, Context context, int level){
        EndPoint endPoint1;


        outerWalls = new ArrayList<>();
        outerWalls.add(new Wall(resources, context, 0, 0, Constants.WALL_SIZE, Constants.SCREEN_HEIGHT));
        outerWalls.add(new Wall(resources, context, 0, 0, Constants.SCREEN_WIDTH, Constants.WALL_SIZE));
        outerWalls.add(new Wall(resources, context, Constants.SCREEN_WIDTH - Constants.WALL_SIZE, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        outerWalls.add(new Wall(resources, context, 0, Constants.SCREEN_HEIGHT - Constants.WALL_SIZE, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

        innerWalls = new ArrayList<>();
        endPoint1 = new EndPoint(context, 36, 36, 4, 31);
        if(level == 2) {
            endPoint1 = new EndPoint(context, 36, 36, 4, 31);
            innerWalls.add(new Wall(resources, context, 0, 2 * Constants.SCREEN_HEIGHT / 9 - Constants.WALL_SIZE, 8 * Constants.SCREEN_WIDTH / 18, 2 * Constants.SCREEN_HEIGHT / 9));
            innerWalls.add(new Wall(resources, context, (int) (10.5 * Constants.SCREEN_WIDTH / 18), 2 * Constants.SCREEN_HEIGHT / 9 - Constants.WALL_SIZE, Constants.SCREEN_WIDTH, 2 * Constants.SCREEN_HEIGHT / 9));
            innerWalls.add(new Wall(resources, context, (int) (8.5 * Constants.SCREEN_WIDTH / 18) - Constants.WALL_SIZE, 2 * Constants.SCREEN_HEIGHT / 9 - Constants.WALL_SIZE, (int) (8.5 * Constants.SCREEN_WIDTH / 18), (int) (4.5 * Constants.SCREEN_HEIGHT / 9)));
            innerWalls.add(new Wall(resources, context, (int) (8.5 * Constants.SCREEN_WIDTH / 18), 3 * Constants.SCREEN_HEIGHT / 9, 15 * Constants.SCREEN_WIDTH / 18, 3 * Constants.SCREEN_HEIGHT / 9 + Constants.WALL_SIZE));
            innerWalls.add(new Wall(resources, context, (int) (2.5 * Constants.SCREEN_WIDTH / 18), (int) (4.5 * Constants.SCREEN_HEIGHT / 9 - Constants.WALL_SIZE), (int) (8.5 * Constants.SCREEN_WIDTH / 18), (int) (4.5 * Constants.SCREEN_HEIGHT / 9)));
            innerWalls.add(new Wall(resources, context, (int) (11 * Constants.SCREEN_WIDTH / 18), (int) (5.5 * Constants.SCREEN_HEIGHT / 9 - Constants.WALL_SIZE), Constants.SCREEN_WIDTH, (int) (5.5 * Constants.SCREEN_HEIGHT / 9)));
            innerWalls.add(new Wall(resources, context, 0, (int) (6.5 * Constants.SCREEN_HEIGHT / 9), 12 * Constants.SCREEN_WIDTH / 18, (int) (6.5 * Constants.SCREEN_HEIGHT / 9 + Constants.WALL_SIZE)));
        }
        if(level == 3){
            endPoint1 = new EndPoint(context, 36, 36, 21, 31);
            innerWalls.add(new Wall(context, 36, 18, 4, 1, 5, 10));
            innerWalls.add(new Wall(context, 36, 18, 4, 13, 28, 14));
            innerWalls.add(new Wall(context, 36, 18, 9, 4, 10, 13));
            innerWalls.add(new Wall(context, 36, 18, 13, 5, 28, 6));
            innerWalls.add(new Wall(context, 36, 18, 13, 9, 14, 13));
            innerWalls.add(new Wall(context, 36, 18, 17, 6, 18, 10));
            innerWalls.add(new Wall(context, 36, 18, 17, 14, 18, 17));
            innerWalls.add(new Wall(context, 36, 18, 21, 9, 22, 13));
            innerWalls.add(new Wall(context, 36, 18, 25, 6, 26, 10));
            innerWalls.add(new Wall(context, 36, 18, 27, 1, 28, 5));
            innerWalls.add(new Wall(context, 36, 18, 31, 4, 32, 14));
        }
        endPoint = endPoint1;


    }

    public List<Wall> getWalls(){
        List<Wall> retList = new ArrayList<Wall>(outerWalls);
        retList.addAll(innerWalls);
        return retList;
    }

    public EndPoint getEndPoint(){
        return endPoint;
    }

    @Override
    public void draw(Canvas canvas) {
        for(Wall wall: outerWalls){
            wall.draw(canvas);
        }
        for(Wall wall: innerWalls){
            wall.draw(canvas);
        }
        endPoint.draw(canvas);
    }

    @Override
    public void update() {

    }
}
