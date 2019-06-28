package com.reluck.mycompletedgames.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.reluck.mycompletedgames.R
import com.reluck.mycompletedgames.adapters.GamesAdapter
import com.reluck.mycompletedgames.data.db.entity.Game
import kotlinx.android.synthetic.main.activity_main.*

class GameActivity : AppCompatActivity() {

    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val games: ArrayList<Game> = ArrayList()

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        gameViewModel.allGames.observe(this, Observer { gamesListed ->
            System.out.println(gamesListed)
            recyclerView.layoutManager = LinearLayoutManager(this)
            if (gameViewModel.allGames.value != null) {
                recyclerView.adapter = GamesAdapter(gameViewModel.allGames.value!!, gameViewModel)
            } else {
                recyclerView.adapter = GamesAdapter(games, gameViewModel)
            }
        })

        btnAddToDB.setOnClickListener{
            val intent = Intent(this, AddGameActivity::class.java)
            startActivity(intent)
            //gameViewModel.insert(Game("CS:GO", "10:00", "https://www.google.com", "PC"))
        }


    }
}
