package com.reluck.mycompletedgames

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.reluck.mycompletedgames.adapters.GamesAdapter
import com.reluck.mycompletedgames.data.db.Game
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val games: ArrayList<Game> = ArrayList()

        games.add(Game("CS:GO", "10:00", "https://www.google.com", "PC",0))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GamesAdapter(games)
    }
}
