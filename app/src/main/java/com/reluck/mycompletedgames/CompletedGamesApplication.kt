package com.reluck.mycompletedgames

import android.app.Application
import androidx.room.Database
import com.reluck.mycompletedgames.data.db.GameDatabase
import com.reluck.mycompletedgames.data.db.dao.GameDao
import com.reluck.mycompletedgames.repository.GameRepository
import com.reluck.mycompletedgames.ui.GameViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import kotlin.math.sin

class CompletedGamesApplication() : Application(), KodeinAware{
    override val kodein by Kodein.lazy {
        import(androidXModule(this@CompletedGamesApplication))
        bind<GameDatabase>() with singleton { GameDatabase(instance()) }
        bind() from singleton { instance<GameDatabase>().completedGames() }
        bind() from singleton { GameRepository(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        val k = kodein
        println(k)
    }

}