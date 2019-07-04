package com.reluck.mycompletedgames.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
                clearFilters()
                val intent = Intent(this, AddGameActivity::class.java)
                startActivity(intent)
            }
            layPc.id, layPs4.id, layXbox.id -> {
                handleFilterClick(v.id)
            }
        }
    }

    val games: MutableList<Game> = ArrayList()
    private var filterApplied: Boolean = false
    private lateinit var gameViewModel: GameViewModel
    private lateinit var mAdapter: GamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = GamesAdapter(games, gameViewModel)
        recyclerView.adapter = mAdapter

        gameViewModel.allGames.observe(this, Observer { gamesListed ->
            if (gameViewModel.allGames.value != null && !filterApplied) {
                mAdapter.games = gameViewModel.allGames.value!!
                mAdapter.notifyDataSetChanged()
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
            checkFilterApplied("PC")
        }
        if (id == layPs4.id){
            checkFilterApplied("PS4")
        }
        if (id == layXbox.id){
            checkFilterApplied("XBOX")
        }
    }

    private fun checkFilterApplied(platform: String){
        if (filterApplied) clearFilters() else applyFilter(platform)
    }

    private fun clearFilters() {
        filterApplied = false
        updateAdapterGames()
    }

    private fun applyFilter(platform: String) {
        filterApplied = true
        val filtredGames = gameViewModel.getPlatformGame(platform)
        filtredGames.observe(this, Observer { res -> filter(res) })
    }

    private fun filter(res: MutableList<Game>){
        Log.d("Filtred Observer", res.toString())
        if (filterApplied) {
            mAdapter.games = res
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun updateAdapterGames(){
        gameViewModel.getAllGames()
        println("All games ${gameViewModel.allGames.value}")
        mAdapter.games = if (gameViewModel.allGames.value != null) gameViewModel.allGames.value!! else ArrayList()
        mAdapter.notifyDataSetChanged()
    }
}
