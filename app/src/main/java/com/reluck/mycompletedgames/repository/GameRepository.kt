package com.reluck.mycompletedgames.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.reluck.mycompletedgames.data.db.entity.Game
import com.reluck.mycompletedgames.data.db.dao.GameDao

class GameRepository(private val gameDao: GameDao){

    val allGames: LiveData<List<Game>> = gameDao.getGames()

    @WorkerThread
    suspend fun insert(game: Game){
        gameDao.upsert(game)
    }
}