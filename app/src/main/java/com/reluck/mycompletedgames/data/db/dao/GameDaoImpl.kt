package com.reluck.mycompletedgames.data.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Query
import com.reluck.mycompletedgames.data.db.entity.Game

class GameDaoImpl: GameDao {

    private val gamesList: MutableList<Game> = mutableListOf<Game>()
    private val games = MutableLiveData<List<Game>>()

    init {
        games.value = gamesList
    }

    override fun upsert(game: Game) {
    }

    @Query("select * from completed_games")
    override fun getGames(): LiveData<MutableList<Game>> = games as LiveData<MutableList<Game>>


    override fun deleteGame(game: Game) {
    }
}