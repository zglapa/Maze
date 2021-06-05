package com.example.maze.game.objects;

import android.graphics.Canvas;
import android.graphics.Point;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LevelField implements GameObject, Serializable {

    private final List<Wall> outerWalls;
    private final List<Wall> innerWalls;
    private final EndPoint endPoint;
    private final Point ballStartPoint;
    private final List<Eater> eaters;

    public LevelField(List<Wall> outerWalls, List<Wall> innerWalls, List<Eater> eaters, Point ballStartPoint, EndPoint endPoint){
        this.outerWalls = outerWalls;
        this.innerWalls = innerWalls;
        this.eaters = eaters;
        this.endPoint = endPoint;
        this.ballStartPoint = ballStartPoint;
    }

    public List<Wall> getWalls(){
        List<Wall> retList = new ArrayList<>(outerWalls);
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
    }

    @Override
    public void update() {

    }

    public Point getBallStartPoint(){
        return ballStartPoint;
    }

}
