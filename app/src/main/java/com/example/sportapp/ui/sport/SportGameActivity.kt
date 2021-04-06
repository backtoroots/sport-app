package com.example.sportapp.ui.sport

import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.sportapp.R
import com.example.sportapp.databinding.ActivitySportGameBinding
import com.example.sportapp.ui.sport.gallery.GalleryFragment
import com.example.sportapp.ui.sport.players.PlayersFragment
import com.example.sportapp.ui.sport.rules.RulesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class SportGameActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivitySportGameBinding
    private val sportName by lazy { intent.getStringExtra(getString(R.string.sportKeyName)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySportGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)


        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, getFragmentWithBundle(RulesFragment())).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val selectedFragment: Fragment = when (item.itemId) {
            R.id.rules_item -> RulesFragment()
            R.id.players_item -> PlayersFragment()
            R.id.gallery_item -> GalleryFragment()
            else -> RulesFragment()
        }
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, getFragmentWithBundle(selectedFragment))
            .commit()
        return true
    }

    private fun getFragmentWithBundle(fragment: Fragment): Fragment {
        val bundle = bundleOf()
        bundle.putString(getString(R.string.sportKeyName), sportName)
        fragment.arguments = bundle
        return fragment
    }
}