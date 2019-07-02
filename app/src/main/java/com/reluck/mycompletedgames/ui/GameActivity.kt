package com.reluck.mycompletedgames.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.reluck.mycompletedgames.R
import com.reluck.mycompletedgames.adapters.GamesAdapter
import com.reluck.mycompletedgames.data.db.entity.Game
import kotlinx.android.synthetic.main.activity_main.*

class GameActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id)
        {
            btnAddToDB.id -> {
                val intent = Intent(this, AddGameActivity::class.java)
                startActivity(intent)
            }
            layPc.id, layPs4.id, layXbox.id -> {
                handleFilterClick(v.id)
            }
        }
    }

    private var filterApplied: Boolean = false
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

        initClickListener()
    }

    private fun initClickListener(){
        btnAddToDB.setOnClickListener(this)
        layPc.setOnClickListener(this)
        layPs4.setOnClickListener(this)
        layXbox.setOnClickListener(this)
    }

    private fun handleFilterClick(id: Int){
        if (id == layPc.id){
            checkFilterApplied()
            gameViewModel.getPlatformGame("PC")
        }
    }

    private fun checkFilterApplied(){
        if (filterApplied) clearFilters()
    }

    private fun clearFilters() {

    }
}
