package com.reluck.mycompletedgames.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Game::class],
    version = 1
)
abstract class GameDatabase: RoomDatabase() {
    abstract fun completedGames(): GameDao

    companion object {
        @Volatile private var instance: GameDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                    GameDatabase::class.java, "game.db")
                    .build()
    }
}