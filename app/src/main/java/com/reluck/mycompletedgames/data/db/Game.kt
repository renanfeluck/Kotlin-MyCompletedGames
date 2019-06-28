package com.reluck.mycompletedgames.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(val title: String,
                val time: String,
                val image: String,
                val platform: String,
                @PrimaryKey(autoGenerate = true)
                val id: Int)