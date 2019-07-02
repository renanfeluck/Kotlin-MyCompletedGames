package com.reluck.mycompletedgames.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.reluck.mycompletedgames.data.db.entity.Game
import com.reluck.mycompletedgames.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class GameViewModel(application: Application): AndroidViewModel(application), KodeinAware {


    override val kodein by closestKodein()
    private val repository: GameRepository by instance()

    var allGames: LiveData<MutableList<Game>>

    init {
        allGames = repository.allGames
    }

    fun getPlatformGame(platform: String) {
      allGames = repository.filtredPlatformGame(platform)
    }

    fun insert(game: Game) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(game)
    }


    fun delete(game: Game) {
        GlobalScope.launch {
            repository.delete(game)
        }
    }
}

