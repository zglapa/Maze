package com.example.maze.game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LevelField implements GameObject, Serializable {

    private final List<Wall> outerWalls;
    private final List<Wall> innerWalls;

    public LevelField(Resources resources, Context context, int level){

        outerWalls = new ArrayList<>();
        outerWalls.add(new Wall(resources, context, 0, 0, Constants.WALL_SIZE, Constants.SCREEN_HEIGHT));
        outerWalls.add(new Wall(resources, context, 0, 0, Constants.SCREEN_WIDTH, Constants.WALL_SIZE));
        outerWalls.add(new Wall(resources, context, Constants.SCREEN_WIDTH - Constants.WALL_SIZE, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        outerWalls.add(new Wall(resources, context, 0, Constants.SCREEN_HEIGHT - Constants.WALL_SIZE, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

        innerWalls = new ArrayList<>();

        if(level != 1){
            innerWalls.add(new Wall(resources, context, 0, 2*Constants.SCREEN_HEIGHT/9 - Constants.WALL_SIZE, 8*Constants.SCREEN_WIDTH/18, 2*Constants.SCREEN_HEIGHT/9));
            innerWalls.add(new Wall(resources, context, (int) (10.5*Constants.SCREEN_WIDTH/18), 2*Constants.SCREEN_HEIGHT/9 - Constants.WALL_SIZE, Constants.SCREEN_WIDTH, 2*Constants.SCREEN_HEIGHT/9));
            innerWalls.add(new Wall(resources, context, (int)(8.5*Constants.SCREEN_WIDTH/18) - Constants.WALL_SIZE,2*Constants.SCREEN_HEIGHT/9 - Constants.WALL_SIZE, (int)(8.5*Constants.SCREEN_WIDTH/18), (int)(4.5*Constants.SCREEN_HEIGHT/9)));
            innerWalls.add(new Wall(resources, context, (int)(8.5*Constants.SCREEN_WIDTH/18), 3*Constants.SCREEN_HEIGHT/9, 15*Constants.SCREEN_WIDTH/18, 3*Constants.SCREEN_HEIGHT/9 + Constants.WALL_SIZE));
            innerWalls.add(new Wall(resources, context, (int)(2.5*Constants.SCREEN_WIDTH/18), (int)(4.5*Constants.SCREEN_HEIGHT/9 - Constants.WALL_SIZE), (int)(8.5*Constants.SCREEN_WIDTH/18), (int)(4.5*Constants.SCREEN_HEIGHT/9)));
            innerWalls.add(new Wall(resources, context, (int)(11*Constants.SCREEN_WIDTH/18), (int)(5.5*Constants.SCREEN_HEIGHT/9 - Constants.WALL_SIZE), Constants.SCREEN_WIDTH, (int)(5.5*Constants.SCREEN_HEIGHT/9)));
            innerWalls.add(new Wall(resources, context, 0, (int)(6.5*Constants.SCREEN_HEIGHT/9), 12*Constants.SCREEN_WIDTH/18, (int)(6.5*Constants.SCREEN_HEIGHT/9 + Constants.WALL_SIZE)));
        }


    }

    public List<Wall> getWalls(){
        List<Wall> retList = new ArrayList<Wall>(outerWalls);
        retList.addAll(innerWalls);
        return retList;
    }

    @Override
    public void draw(Canvas canvas) {
        for(Wall wall: outerWalls){
            wall.draw(canvas);
        }
        for(Wall wall: innerWalls){
            wall.draw(canvas);
        }
    }

    @Override
    public void update() {

    }
}
