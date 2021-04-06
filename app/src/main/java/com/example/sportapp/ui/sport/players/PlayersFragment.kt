package com.example.sportapp.ui.sport.players

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.Player
import com.example.sportapp.R
import com.example.sportapp.SportsGame
import com.example.sportapp.databinding.FragmentPlayersBinding
import com.example.sportapp.databinding.PlayersListItemBinding
import java.util.*

class PlayersFragment : Fragment() {
    private lateinit var sportGame: SportsGame
    private val players = mutableListOf<Player>()
    private lateinit var adapter: PlayersRecyclerAdapter
    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { bundle ->
            val sportName = bundle.getString(getString(R.string.sportKeyName))
            sportGame = enumValueOf(sportName!!)
        }

        setPlayers()
        adapter = PlayersRecyclerAdapter(players)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        activity?.title = when (sportGame) {
            SportsGame.FOOTBALL -> getString(R.string.footballers)
            SportsGame.BASKETBALL -> getString(R.string.basketball_players)
            SportsGame.TENNIS -> getString(R.string.tennis_players)
            SportsGame.HANDBALL -> getString(R.string.handball_players)
        }

        binding.playersRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.playersRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setPlayers() {
        var names: Array<String> = arrayOf()
        lateinit var images: TypedArray

        when (sportGame) {
            SportsGame.FOOTBALL -> {
                names = resources.getStringArray(R.array.football_players_names)
                images = resources.obtainTypedArray(R.array.football_players_images) // TODO something with recycle
            }
            SportsGame.BASKETBALL -> {
                names = resources.getStringArray(R.array.basketball_players_names) // TODO change resource
                images = resources.obtainTypedArray(R.array.basketball_players_images)
            }
            SportsGame.TENNIS -> {
                names = resources.getStringArray(R.array.tennis_players_names)
                images = resources.obtainTypedArray(R.array.tennis_players_images)
            }
            SportsGame.HANDBALL -> {
                names = resources.getStringArray(R.array.handball_players_names)
                images = resources.obtainTypedArray(R.array.handball_players_images)
            }
        }

        for (i in names.indices) {
            var splitName = names[i].trim().split("\\s+".toRegex())
            if (splitName.size < 2) {
                splitName = listOf(splitName.first(), "")
            }
            players.add(Player(splitName.first(), splitName.last(), images.getDrawable(i)!!))
        }

    }
}