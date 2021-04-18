package com.example.sportapp.ui

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sportapp.*
import com.example.sportapp.databinding.ActivitySportsGamesBinding
import com.example.sportapp.model.SportGame
import com.example.sportapp.model.SportsGamesTypes
import com.example.sportapp.ui.sport.SportGameActivity
import com.example.sportapp.ui.utils.GridMenuItemDecorator

class SportsGamesActivity : AppCompatActivity(), SportsGamesRecyclerAdapter.OnSportGameListener {

    private val numberOfGridColumns = 2
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

        binding.toolbar.title = getString(R.string.sportGames)
        binding.sportGamesRecycler.layoutManager = GridLayoutManager(this, numberOfGridColumns)
        binding.sportGamesRecycler.addItemDecoration(GridMenuItemDecorator(10, numberOfGridColumns))
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