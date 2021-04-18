package com.example.sportapp.ui.sport

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.sportapp.R
import com.example.sportapp.model.SportsGamesTypes
import com.example.sportapp.databinding.ActivitySportGameBinding
import com.example.sportapp.ui.sport.gallery.GalleryFragment
import com.example.sportapp.ui.sport.players.PlayersFragment
import com.example.sportapp.ui.sport.rules.RulesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class SportGameActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivitySportGameBinding
    private val sportGameType by lazy { intent.getSerializableExtra(getString(R.string.sportTypeKey)) as SportsGamesTypes }
    private var currentFragmentType: String = ""
    private val fragmentsMap: Map<String, Fragment> by lazy { mapOf(
        Pair(getString(R.string.ruleFragmentTag), RulesFragment()),
        Pair(getString(R.string.playersFragmentTag), PlayersFragment()),
        Pair(getString(R.string.galleryFragmentTag), GalleryFragment())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySportGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        currentFragmentType = savedInstanceState?.getString(getString(R.string.fragmentTypeKey)) ?: getString(R.string.ruleFragmentTag)

        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(currentFragmentType)
        if (fragment == null || !fragment.isVisible) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    binding.fragmentContainer.id,
                    getFragmentWithBundle(fragmentsMap[currentFragmentType]!!),
                    currentFragmentType)
                .commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val selectedFragmentWithTag: Pair<Fragment, String> = when (item.itemId) {
            R.id.rules_item -> Pair(RulesFragment(), getString(R.string.ruleFragmentTag))
            R.id.players_item -> Pair(PlayersFragment(), getString(R.string.playersFragmentTag))
            R.id.gallery_item -> Pair(GalleryFragment(), getString(R.string.galleryFragmentTag))
            else -> Pair(RulesFragment(), getString(R.string.ruleFragmentTag))
        }
        currentFragmentType = selectedFragmentWithTag.second

        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(currentFragmentType)
        if (fragment == null || !fragment.isVisible) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer.id,
                    getFragmentWithBundle(selectedFragmentWithTag.first),
                    selectedFragmentWithTag.second)
                .commit()
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getFragmentWithBundle(fragment: Fragment): Fragment {
        val bundle = bundleOf()
        bundle.putSerializable(getString(R.string.sportTypeKey), sportGameType)
        fragment.arguments = bundle
        return fragment
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(getString(R.string.fragmentTypeKey), currentFragmentType)
    }
}