package com.reluck.mycompletedgames.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.reluck.mycompletedgames.data.db.GameDatabase
import com.reluck.mycompletedgames.data.db.entity.Game
import com.reluck.mycompletedgames.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGameViewModel(application: Application): AndroidViewModel(application) {

    private val repository: GameRepository


    init {
        val gamesDao = GameDatabase.invoke(application).completedGames()
        repository = GameRepository(gamesDao)
    }

    fun insert(game: Game) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(game)
    }

}