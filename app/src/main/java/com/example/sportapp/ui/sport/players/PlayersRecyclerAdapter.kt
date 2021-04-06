package com.example.sportapp.ui.sport.players

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.Player
import com.example.sportapp.databinding.PlayersListItemBinding

class PlayersRecyclerAdapter(private val players: List<Player>) :
    RecyclerView.Adapter<PlayersRecyclerAdapter.PlayersViewHolder>() {

    class PlayersViewHolder(val binding: PlayersListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val view =
            PlayersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayersViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val player = players[position]

        holder.binding.apply {
            playerPhoto.setImageDrawable(player.image)
            if (player.firstName.isNotEmpty() && player.lastName.isNotEmpty()) {
                playerFirstName.text = player.firstName
                playerLastName.text = player.lastName
            } else {
                playerFirstName.visibility = View.GONE
                playerLastName.visibility = View.GONE
                playerName.visibility = View.VISIBLE
                playerName.text = player.firstName
            }
        }
    }

    override fun getItemCount(): Int = players.size


}