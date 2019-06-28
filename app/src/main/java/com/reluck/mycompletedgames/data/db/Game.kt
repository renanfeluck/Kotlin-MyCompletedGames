package com.reluck.mycompletedgames.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_games")
data class Game(val title: String,
                val time: String,
                val image: String,
                val platform: String
                ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}