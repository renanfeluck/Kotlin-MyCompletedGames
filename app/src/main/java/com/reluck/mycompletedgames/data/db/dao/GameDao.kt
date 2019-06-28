package com.reluck.mycompletedgames.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reluck.mycompletedgames.data.db.entity.Game

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(game: Game)

    @Query("select * from completed_games")
    fun getGames(): LiveData<List<Game>>
}