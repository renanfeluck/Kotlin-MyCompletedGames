package com.reluck.mycompletedgames.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reluck.mycompletedgames.data.db.entity.Game

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(game: Game)

    @Query("select * from completed_games")
    fun getGames(): LiveData<MutableList<Game>>

    @Delete
    fun deleteGame(game: Game)
}