package com.example.maze.game.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Player.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PlayerDao playerDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"DB").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }
}
