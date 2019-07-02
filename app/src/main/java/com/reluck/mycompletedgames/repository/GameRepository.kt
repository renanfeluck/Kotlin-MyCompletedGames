package com.reluck.mycompletedgames.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Transaction
import com.reluck.mycompletedgames.data.db.entity.Game
import com.reluck.mycompletedgames.data.db.dao.GameDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameRepository(private val gameDao: GameDao){

    val allGames: LiveData<MutableList<Game>> = gameDao.getGames()
    fun filtredPlatformGame(platform: String): LiveData<MutableList<Game>> = gameDao.getPlatformGame(platform)

    @WorkerThread
    suspend fun insert(game: Game){
        gameDao.upsert(game)
    }

    @Transaction
    open suspend fun delete(game: Game) {
        gameDao.deleteGame(game)
    }
}