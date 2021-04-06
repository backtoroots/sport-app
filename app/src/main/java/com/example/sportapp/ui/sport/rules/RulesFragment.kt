package com.example.sportapp.ui.sport.rules

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.R
import com.example.sportapp.Rule
import com.example.sportapp.RuleListItem
import com.example.sportapp.SportsGame
import com.example.sportapp.databinding.FragmentRulesBinding
import java.util.*

class RulesFragment : Fragment(), RulesRecyclerAdapter.OnRuleTitleListener {
    private lateinit var sportGame: SportsGame
    private val rules = mutableListOf<RuleListItem>()
    lateinit var adapter: RulesRecyclerAdapter
    private var _binding: FragmentRulesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { bundle ->
            val sportName = bundle.getString(getString(R.string.sportKeyName))
            sportGame = enumValueOf(sportName!!)
        }

        setRuleItems()
        adapter = RulesRecyclerAdapter(rules, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRulesBinding.inflate(inflater, container, false)
        activity?.title =
            "Правила игры в ${sportGame?.russianName?.toLowerCase(Locale.getDefault())}"

        binding.rulesRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.rulesRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onRuleClick(position: Int) {
        rules[position].isExpanded = !rules[position].isExpanded
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRuleItems() {
        var titles: Array<String> = arrayOf()
        var contents: Array<String> = arrayOf()
        when (sportGame) {
            SportsGame.FOOTBALL -> {
                titles = resources.getStringArray(R.array.football_titles)
                contents = resources.getStringArray(R.array.football_contents)
            }
            SportsGame.BASKETBALL -> {
                titles = resources.getStringArray(R.array.basketball_titles)
                contents = resources.getStringArray(R.array.basketball_contents)
            }
            SportsGame.TENNIS -> {
                titles = resources.getStringArray(R.array.tennis_titles)
                contents = resources.getStringArray(R.array.tennis_contents)
            }
            SportsGame.HANDBALL -> {
                titles = resources.getStringArray(R.array.handball_titles)
                contents = resources.getStringArray(R.array.handball_contents)
            }
        }


        for (i in titles.indices) {
            rules.add(RuleListItem(Rule(titles[i], contents[i]), false))
        }
    }
}