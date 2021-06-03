package com.example.maze.game.objects;

import android.content.Context;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class LevelFactory {
    private static List<Wall> produceOuterWalls(Context context){
        List<Wall> outerWalls = new ArrayList<>();

        outerWalls.add(new Wall(context, 54, 27, 0, 0, 54, 2));
        outerWalls.add(new Wall(context, 54, 27, 52, 2, 54, 25));
        outerWalls.add(new Wall(context, 54, 27, 0, 25, 54, 27));
        outerWalls.add(new Wall(context, 54, 27, 0, 2, 2, 25));

        return outerWalls;
    }
    public static LevelField produceLevel1(Context context){
        List<Wall> outerWalls = produceOuterWalls(context);

        List<Wall> innerWalls = new ArrayList<>();
        innerWalls.add(new Wall(context, 54, 27, 8, 2, 10, 19));
        innerWalls.add(new Wall(context, 54, 27, 10, 17, 44, 19));
        innerWalls.add(new Wall(context, 54, 27, 44, 8, 46, 19));
        innerWalls.add(new Wall(context, 54, 27, 16, 8, 44, 10));

        List<Eater> eaters = new ArrayList<>();

        EndPoint endPoint = new EndPoint(context, 54, 27, 40, 14);

        Point ballStartPoint = new Point(5, 4);


        return new LevelField(outerWalls, innerWalls, eaters, ballStartPoint, endPoint);
    }
    public static LevelField produceLevel2(Context context){
        List<Wall> outerWalls = produceOuterWalls(context);

        List<Wall> innerWalls = new ArrayList<>();
        innerWalls.add(new Wall(context, 54, 27, 2, 8, 22, 10));
        innerWalls.add(new Wall(context, 54, 27, 8, 16, 30, 18));
        innerWalls.add(new Wall(context, 54, 27, 28, 2, 30, 16));
        innerWalls.add(new Wall(context, 54, 27, 40, 2, 42, 9));
        innerWalls.add(new Wall(context, 54, 27, 40, 15, 42, 25));


        List<Eater> eaters = new ArrayList<>();
        eaters.add(new Eater(context, 54, 27, 28, 21));

        EndPoint endPoint = new EndPoint(context, 54, 27, 47, 22);

        Point ballStartPoint = new Point(5, 4);


        return new LevelField(outerWalls, innerWalls, eaters, ballStartPoint, endPoint);
    }
    public static LevelField produceLevel3(Context context){
        List<Wall> outerWalls = produceOuterWalls(context);

        List<Wall> innerWalls = new ArrayList<>();
        innerWalls.add(new Wall(context, 54, 27, 2, 8, 22, 10));
        innerWalls.add(new Wall(context, 54, 27, 10, 16, 12, 25));
        innerWalls.add(new Wall(context, 54, 27, 22, 8, 24, 18));
        innerWalls.add(new Wall(context, 54, 27, 24, 16, 46, 18));
        innerWalls.add(new Wall(context, 54, 27, 30, 8, 52, 10));


        List<Eater> eaters = new ArrayList<>();
        eaters.add(new Eater(context, 54, 27, 35, 5));
        eaters.add(new Eater(context, 54, 27, 17, 15));
        eaters.add(new Eater(context, 54, 27, 27, 23));

        EndPoint endPoint = new EndPoint(context, 54, 27, 6, 23);

        Point ballStartPoint = new Point(5, 4);


        return new LevelField(outerWalls, innerWalls, eaters, ballStartPoint, endPoint);
    }
    public static LevelField produceLevel4(Context context){
        List<Wall> outerWalls = produceOuterWalls(context);

        List<Wall> innerWalls = new ArrayList<>();
        innerWalls.add(new Wall(context, 54, 27, 8, 8, 18, 10));
        innerWalls.add(new Wall(context, 54, 27, 12, 10, 14, 19));
        innerWalls.add(new Wall(context, 54, 27, 22, 8, 32, 10));
        innerWalls.add(new Wall(context, 54, 27, 22, 10, 24, 19));
        innerWalls.add(new Wall(context, 54, 27, 24, 17, 32, 19));
        innerWalls.add(new Wall(context, 54, 27, 36, 8, 38, 15));
        innerWalls.add(new Wall(context, 54, 27, 38, 8, 46, 10));
        innerWalls.add(new Wall(context, 54, 27, 38, 13, 46, 15));
        innerWalls.add(new Wall(context, 54, 27, 36, 17, 44, 19));
        innerWalls.add(new Wall(context, 54, 27, 44, 13, 46, 19));

        List<Eater> eaters = new ArrayList<>();
        eaters.add(new Eater(context, 54, 27, 27, 4));
        eaters.add(new Eater(context, 54, 27, 27, 23));

        EndPoint endPoint = new EndPoint(context, 54, 27, 28, 14);

        Point ballStartPoint = new Point(5, 4);


        return new LevelField(outerWalls, innerWalls, eaters, ballStartPoint, endPoint);
    }
    public static LevelField produceLevel5(Context context){
        List<Wall> outerWalls = produceOuterWalls(context);

        List<Wall> innerWalls = new ArrayList<>();
        innerWalls.add(new Wall(context, 54, 27, 2, 18, 22, 20));
        innerWalls.add(new Wall(context, 54, 27, 32, 8, 52, 10));

        innerWalls.add(new Wall(context, 54, 27, 12, 4, 14, 12));
        innerWalls.add(new Wall(context, 54, 27, 12, 12, 15, 14));
        innerWalls.add(new Wall(context, 54, 27, 14, 4, 20, 6));
        innerWalls.add(new Wall(context, 54, 27, 20, 4, 22, 8));
        innerWalls.add(new Wall(context, 54, 27, 19, 12, 22, 14));

        innerWalls.add(new Wall(context, 54, 27, 22, 4, 24, 8));
        innerWalls.add(new Wall(context, 54, 27, 22, 12, 25, 14));
        innerWalls.add(new Wall(context, 54, 27, 24, 4, 30, 6));
        innerWalls.add(new Wall(context, 54, 27, 29, 12, 32, 14));
        innerWalls.add(new Wall(context, 54, 27, 30, 4, 32, 12));

        innerWalls.add(new Wall(context, 54, 27, 22, 14, 25, 16));
        innerWalls.add(new Wall(context, 54, 27, 29, 14, 32, 16));
        innerWalls.add(new Wall(context, 54, 27, 22, 16, 24, 22));
        innerWalls.add(new Wall(context, 54, 27, 22, 22, 30, 24));
        innerWalls.add(new Wall(context, 54, 27, 30, 20, 32, 24));

        innerWalls.add(new Wall(context, 54, 27, 32, 14, 40, 16));
        innerWalls.add(new Wall(context, 54, 27, 32, 20, 34, 24));
        innerWalls.add(new Wall(context, 54, 27, 40, 14, 42, 22));
        innerWalls.add(new Wall(context, 54, 27, 34, 22, 42, 24));


        List<Eater> eaters = new ArrayList<>();
        eaters.add(new Eater(context, 54, 27, 16, 8));
        eaters.add(new Eater(context, 54, 27, 26, 20));

        EndPoint endPoint = new EndPoint(context, 54, 27, 37, 19);

        Point ballStartPoint = new Point(5, 4);


        return new LevelField(outerWalls, innerWalls, eaters, ballStartPoint, endPoint);
    }
    public static LevelField produceLevel6(Context context){
        List<Wall> outerWalls = produceOuterWalls(context);

        List<Wall> innerWalls = new ArrayList<>();
        innerWalls.add(new Wall(context, 54, 27, 8, 2, 10, 19));
        innerWalls.add(new Wall(context, 54, 27, 16, 8, 18, 25));
        innerWalls.add(new Wall(context, 54, 27, 24, 2, 26, 19));
        innerWalls.add(new Wall(context, 54, 27, 32, 8, 34, 25));
        innerWalls.add(new Wall(context, 54, 27, 34, 8, 46, 10));
        innerWalls.add(new Wall(context, 54, 27, 44, 10, 46, 17));
        innerWalls.add(new Wall(context, 54, 27, 40, 17, 46, 19));

        List<Eater> eaters = new ArrayList<>();
        eaters.add(new Eater(context, 54, 27, 17, 4));
        eaters.add(new Eater(context, 54, 27, 25, 23));
        eaters.add(new Eater(context, 54, 27, 33, 4));
        eaters.add(new Eater(context, 54, 27, 43, 23));
        eaters.add(new Eater(context, 54, 27, 45, 6));
        eaters.add(new Eater(context, 54, 27, 50, 18));

        EndPoint endPoint = new EndPoint(context, 54, 27, 38, 14);

        Point ballStartPoint = new Point(5, 4);


        return new LevelField(outerWalls, innerWalls, eaters, ballStartPoint, endPoint);
    }
    public static LevelField produceLevel7(Context context){
        List<Wall> outerWalls = produceOuterWalls(context);

        List<Wall> innerWalls = new ArrayList<>();
        innerWalls.add(new Wall(context, 54, 27, 8, 2, 10, 14));
        innerWalls.add(new Wall(context, 54, 27, 8, 18, 10, 25));
        innerWalls.add(new Wall(context, 54, 27, 16, 2, 18, 8));
        innerWalls.add(new Wall(context, 54, 27, 16, 12, 18, 25));
        innerWalls.add(new Wall(context, 54, 27, 24, 2, 26, 19));
        innerWalls.add(new Wall(context, 54, 27, 32, 2, 34, 7));
        innerWalls.add(new Wall(context, 54, 27, 32, 11, 34, 25));
        innerWalls.add(new Wall(context, 54, 27, 40, 2, 42, 7));
        innerWalls.add(new Wall(context, 54, 27, 40, 11, 42, 25));

        List<Eater> eaters = new ArrayList<>();
        eaters.add(new Eater(context, 54, 27, 5, 21));
        eaters.add(new Eater(context, 54, 27, 13, 5));
        eaters.add(new Eater(context, 54, 27, 37, 4));
        eaters.add(new Eater(context, 54, 27, 37, 14));
        eaters.add(new Eater(context, 54, 27, 44, 14));
        eaters.add(new Eater(context, 54, 27, 44, 20));
        eaters.add(new Eater(context, 54, 27, 50, 17));

        EndPoint endPoint = new EndPoint(context, 54, 27, 50, 23);

        Point ballStartPoint = new Point(5, 4);


        return new LevelField(outerWalls, innerWalls, eaters, ballStartPoint, endPoint);
    }
    public static LevelField produceLevel8(Context context){
        List<Wall> outerWalls = produceOuterWalls(context);

        List<Wall> innerWalls = new ArrayList<>();
        innerWalls.add(new Wall(context, 54, 27, 6, 2, 8, 25));
        innerWalls.add(new Wall(context, 54, 27, 46, 2, 48, 25));
        innerWalls.add(new Wall(context, 54, 27, 12, 6, 14, 14));
        innerWalls.add(new Wall(context, 54, 27, 12, 14, 22, 16));
        innerWalls.add(new Wall(context, 54, 27, 22, 6, 32, 8));
        innerWalls.add(new Wall(context, 54, 27, 22, 8, 24, 21));
        innerWalls.add(new Wall(context, 54, 27, 30, 8, 32, 25));
        innerWalls.add(new Wall(context, 54, 27, 32, 14, 42, 16));
        innerWalls.add(new Wall(context, 54, 27, 40, 6, 42, 14));
        innerWalls.add(new Wall(context, 54, 27, 14, 20, 16, 25));
        innerWalls.add(new Wall(context, 54, 27, 38, 20, 40, 25));


        List<Eater> eaters = new ArrayList<>();
        eaters.add(new Eater(context, 54, 27, 11, 21));
        eaters.add(new Eater(context, 54, 27, 17, 4));
        eaters.add(new Eater(context, 54, 27, 36, 4));
        eaters.add(new Eater(context, 54, 27, 43, 21));

        EndPoint endPoint = new EndPoint(context, 54, 27, 35, 23);

        Point ballStartPoint = new Point(27, 11);


        return new LevelField(outerWalls, innerWalls, eaters, ballStartPoint, endPoint);
    }
}
