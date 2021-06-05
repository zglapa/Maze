package com.example.maze.game.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Player {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "player_name")
    public String name;
    @ColumnInfo(name = "level")
    public int level;
    @ColumnInfo(name = "time")
    public long time;
}
