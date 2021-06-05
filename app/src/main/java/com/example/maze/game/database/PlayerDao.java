package com.example.maze.game.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player")
    List<Player> getAll();

    @Query("SELECT * FROM player WHERE player.level LIKE :level ORDER BY player.time ASC LIMIT 1")
    Player getTopPlayer(int level);

    @Query("SELECT * FROM player WHERE level LIKE :level ORDER BY time ASC")
    List<Player> getAllOnLevel(int level);

    @Insert
    void insertPlayers(Player... players);

    @Delete
    void delete(Player player);
}
