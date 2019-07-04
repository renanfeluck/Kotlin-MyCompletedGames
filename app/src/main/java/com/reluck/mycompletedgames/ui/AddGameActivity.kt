package com.reluck.mycompletedgames.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import com.reluck.mycompletedgames.R
import com.reluck.mycompletedgames.data.db.entity.Game
import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.activity_main.btnAddToDB

class AddGameActivity : AppCompatActivity() {


    private var title: String = ""
    private var timePlayed: String = ""

    private lateinit var addGameViewModel: AddGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)

        addGameViewModel = ViewModelProviders.of(this).get(AddGameViewModel::class.java)

        var categories = arrayOf("Action", "RPG", "Others")
        var platforms = arrayOf("PS4", "XBOX", "PC")

        val adapterCategory = ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                categories)
        adapterCategory.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spCategory.adapter = adapterCategory

        val adapterPlatforms = ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                platforms)
        adapterPlatforms.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spPlatform.adapter = adapterPlatforms


        btnAddToDB.setOnClickListener{
            addGameViewModel.insert(Game(
                    editTitle.text.toString(),
                    editTime.text.toString(),
                    "Url",
                    spPlatform.selectedItem.toString(),
                    spCategory.selectedItem.toString()

                    ))
            finish()
        }

    }
}
