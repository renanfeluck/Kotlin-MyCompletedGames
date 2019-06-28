package com.reluck.mycompletedgames

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.reluck.mycompletedgames.adapters.GamesAdapter
import com.reluck.mycompletedgames.data.db.Game
import com.reluck.mycompletedgames.data.db.GameDao
import com.reluck.mycompletedgames.repository.GameRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val games: ArrayList<Game> = ArrayList()

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        // gameViewModel.insert(Game("CS:GO", "10:00", "https://www.google.com", "PC"))

        gameViewModel.allGames.observe(this, Observer { gamesListed -> System.out.println(gamesListed) })



        games.add(Game("CS:GO", "10:00", "https://www.google.com", "PC"))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GamesAdapter(games)
    }
}
