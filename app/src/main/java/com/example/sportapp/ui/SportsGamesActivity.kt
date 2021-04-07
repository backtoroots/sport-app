package com.example.sportapp.ui

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.R
import com.example.sportapp.SportGame
import com.example.sportapp.SportsGamesTypes
import com.example.sportapp.databinding.ActivitySportsGamesBinding
import com.example.sportapp.ui.sport.SportGameActivity

class SportsGamesActivity : AppCompatActivity(), SportsGamesRecyclerAdapter.OnSportGameListener {

    private lateinit var binding: ActivitySportsGamesBinding
    private val sportsGames = mutableListOf<SportGame>()
    private lateinit var adapter: SportsGamesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySportsGamesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSportsGames()
        adapter = SportsGamesRecyclerAdapter(sportsGames, this)

        binding.toolbar.title = "Спортивные игры"
        binding.sportGamesRecycler.layoutManager = LinearLayoutManager(this)
        binding.sportGamesRecycler.adapter = adapter
    }

    private fun setSportsGames() {
        val images: TypedArray = resources.obtainTypedArray(R.array.sports_games_images)

        SportsGamesTypes.values().forEachIndexed { index, sportsGamesType ->
            sportsGames.add(SportGame(sportsGamesType, images.getDrawable(index)!!))
        }
        images.recycle()
    }

    override fun onSportGameClick(position: Int) {
        val sportGame = sportsGames[position]
        val intent = Intent(this, SportGameActivity::class.java)
        intent.putExtra(getString(R.string.sportTypeKey), sportGame.sportGameType)
        startActivity(intent)
    }
}