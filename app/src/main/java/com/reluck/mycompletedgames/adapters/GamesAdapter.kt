package com.reluck.mycompletedgames.adapters

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.reluck.mycompletedgames.R
import com.reluck.mycompletedgames.data.db.GameDatabase
import com.reluck.mycompletedgames.data.db.entity.Game
import com.reluck.mycompletedgames.repository.GameRepository
import com.reluck.mycompletedgames.ui.GameViewModel
import kotlinx.android.synthetic.main.game_row.view.*
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

        holder.itemView.imgDelete.setOnClickListener{

            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Do you want to delete this entry?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->  delete(position) })
                .setNegativeButton("Cancel", null)
                .show()

        }

    }

    fun delete(position: Int) {
        gameViewModel.delete(games[position])
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, context: Context): RecyclerView.ViewHolder(itemView) {

        val gameTitle: TextView = itemView.findViewById(R.id.textTitle)
        val gameTime: TextView = itemView.findViewById(R.id.textTime)
        val gameCategory: TextView = itemView.findViewById(R.id.textCategory)
        val imgDelete: ImageView = itemView.findViewById(R.id.imgDelete)

    }
}