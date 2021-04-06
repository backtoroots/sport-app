package com.example.sportapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportapp.R
import com.example.sportapp.SportsGame
import com.example.sportapp.databinding.ActivitySportsGamesBinding
import com.example.sportapp.ui.sport.SportGameActivity

class SportsGamesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsGamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySportsGamesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.footballCard.setOnClickListener { navigate(SportsGame.FOOTBALL) }

        binding.basketballCard.setOnClickListener { navigate(SportsGame.BASKETBALL) }

        binding.tennisCard.setOnClickListener { navigate(SportsGame.TENNIS) }

        binding.handballCard.setOnClickListener { navigate(SportsGame.HANDBALL) }
    }

    private fun navigate(sport: SportsGame) {
        val intent = Intent(this, SportGameActivity::class.java)
        intent.putExtra(getString(R.string.sportKeyName), sport.name)
        startActivity(intent)
    }
}