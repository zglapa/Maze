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
    private final List<Eater> eaters;

    public LevelField(Resources resources, Context context, int level){
        EndPoint endPoint1;
        eaters = new ArrayList<>();
        outerWalls = new ArrayList<>();
        outerWalls.add(new Wall(context, 54, 27, 0, 0, 54, 2));
        outerWalls.add(new Wall(context, 54, 27, 52, 2, 54, 25));
        outerWalls.add(new Wall(context, 54, 27, 0, 25, 54, 27));
        outerWalls.add(new Wall(context, 54, 27, 0, 2, 2, 25));

        innerWalls = new ArrayList<>();
        endPoint1 = new EndPoint(context, 54, 27, 10, 10);
        if(level == 2) {
            endPoint1 = new EndPoint(context, 54, 27, 19, 10);
            innerWalls.add(new Wall(context, 54, 27, 2, 6, 29, 8));
            innerWalls.add(new Wall(context, 54, 27, 2, 12, 9, 14));
            innerWalls.add(new Wall(context, 54, 27, 6, 19, 15, 21));
            innerWalls.add(new Wall(context, 54, 27, 13, 16, 15, 19));
            innerWalls.add(new Wall(context, 54, 27, 15, 16, 25, 18));
            innerWalls.add(new Wall(context, 54, 27, 23, 8, 25, 16));
            innerWalls.add(new Wall(context, 54, 27, 23, 22, 25, 25));
            innerWalls.add(new Wall(context, 54, 27, 33, 6, 47, 8));
            innerWalls.add(new Wall(context, 54, 27, 29, 12, 52, 14));
            innerWalls.add(new Wall(context, 54, 27, 29, 19, 40, 21));
            innerWalls.add(new Wall(context, 54, 27, 38, 14, 40, 19));
        }
        if(level == 3){
            endPoint1 = new EndPoint(context, 54, 27, 36, 12);
            eaters.add(new Eater(context, 54, 27, 17, 4));
            innerWalls.add(new Wall(context, 54, 27, 8, 2, 10, 19));
            innerWalls.add(new Wall(context, 54, 27, 16, 8, 18, 25));
            innerWalls.add(new Wall(context, 54, 27, 24, 2, 26, 19));
            innerWalls.add(new Wall(context, 54, 27, 32, 8, 34, 25));
            innerWalls.add(new Wall(context, 54, 27, 34, 8, 46, 10));
            innerWalls.add(new Wall(context, 54, 27, 44, 10, 46, 19));
            innerWalls.add(new Wall(context, 54, 27, 40, 17, 44, 19));
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

    public List<Eater> getEaters() {
        return eaters;
    }

    @Override
    public void draw(Canvas canvas) {
        for(Wall wall: outerWalls){
            wall.draw(canvas);
        }
        for(Wall wall: innerWalls){
            wall.draw(canvas);
        }
//        for(Eater eater: eaters){
//            eater.draw(canvas);
//        }
        endPoint.draw(canvas);
    }

    @Override
    public void update() {

    }
}
