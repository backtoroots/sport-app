package com.example.sportapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.model.SportGame
import com.example.sportapp.databinding.SportsGamesListItemBinding

class SportsGamesRecyclerAdapter(
    private val sportsGames: List<SportGame>,
    private val mOnSportGameListener: OnSportGameListener
): RecyclerView.Adapter<SportsGamesRecyclerAdapter.SportGameViewHolder>() {

    class SportGameViewHolder(
        val binding: SportsGamesListItemBinding,
        private val onSportGameListener: OnSportGameListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.card.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onSportGameListener.onSportGameClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportGameViewHolder {
        val view = SportsGamesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SportGameViewHolder(view, mOnSportGameListener)
    }

    override fun onBindViewHolder(holder: SportGameViewHolder, position: Int) {
        val sportGame = sportsGames[position]
        holder.binding.apply {
            sportGameImage.setImageDrawable(sportGame.drawable)
            sportGameContent.text = sportGame.sportGameType.russianName
        }
    }

    override fun getItemCount(): Int = sportsGames.size

    interface OnSportGameListener {
        fun onSportGameClick(position: Int)
    }
}