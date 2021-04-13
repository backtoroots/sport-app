package com.example.sportapp.ui.sport.players

import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.LinearItemDecorator
import com.example.sportapp.Player
import com.example.sportapp.R
import com.example.sportapp.SportsGamesTypes
import com.example.sportapp.databinding.FragmentPlayersBinding

class PlayersFragment : Fragment() {
    private lateinit var sportGame: SportsGamesTypes
    private val players = mutableListOf<Player>()
    private lateinit var adapter: PlayersRecyclerAdapter
    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sportGame = arguments?.getSerializable(getString(R.string.sportTypeKey)) as SportsGamesTypes

        setPlayers()
        adapter = PlayersRecyclerAdapter(players)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        activity?.title = when (sportGame) {
            SportsGamesTypes.FOOTBALL -> getString(R.string.footballers)
            SportsGamesTypes.BASKETBALL -> getString(R.string.basketball_players)
            SportsGamesTypes.TENNIS -> getString(R.string.tennis_players)
            SportsGamesTypes.HANDBALL -> getString(R.string.handball_players)
        }
        
        binding.playersRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.playersRecyclerView.addItemDecoration(LinearItemDecorator(10))
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
            SportsGamesTypes.FOOTBALL -> {
                names = resources.getStringArray(R.array.football_players_names)
                images = resources.obtainTypedArray(R.array.football_players_images)
            }
            SportsGamesTypes.BASKETBALL -> {
                names = resources.getStringArray(R.array.basketball_players_names)
                images = resources.obtainTypedArray(R.array.basketball_players_images)
            }
            SportsGamesTypes.TENNIS -> {
                names = resources.getStringArray(R.array.tennis_players_names)
                images = resources.obtainTypedArray(R.array.tennis_players_images)
            }
            SportsGamesTypes.HANDBALL -> {
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
        images.recycle()
    }
}