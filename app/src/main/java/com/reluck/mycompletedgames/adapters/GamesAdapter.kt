package com.reluck.mycompletedgames.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.reluck.mycompletedgames.R
import com.reluck.mycompletedgames.data.db.GameDatabase
import com.reluck.mycompletedgames.data.db.entity.Game
import com.reluck.mycompletedgames.repository.GameRepository
import com.reluck.mycompletedgames.ui.GameViewModel
import kotlinx.coroutines.Dispatchers

class GamesAdapter(var games: MutableList<Game>, val gameViewModel: GameViewModel): RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.game_row, parent, false)
        return ViewHolder(view, parent.context)
    }

    override fun getItemCount() = games.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.gameTitle.text = games[position].title
        holder.gameTime.text = games[position].time
        holder.gameCategory.text = games[position].category

        holder.itemView.setOnClickListener{
            gameViewModel.delete(games[position])
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View, context: Context): RecyclerView.ViewHolder(itemView) {

        val gameTitle: TextView = itemView.findViewById(R.id.textTitle)
        val gameTime: TextView = itemView.findViewById(R.id.textTime)
        val gameCategory: TextView = itemView.findViewById(R.id.textCategory)

    }
}