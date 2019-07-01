package com.reluck.mycompletedgames

import android.app.Application
import com.reluck.mycompletedgames.data.db.dao.GameDao
import com.reluck.mycompletedgames.data.db.dao.GameDaoImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class CompletedGamesApplication() : Application(), KodeinAware{
    override val kodein by Kodein.lazy {
        bind<GameDao>() with singleton { GameDaoImpl() }
    }
}