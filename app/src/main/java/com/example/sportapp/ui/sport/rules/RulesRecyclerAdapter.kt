package com.example.sportapp.ui.sport.rules

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.RuleListItem
import com.example.sportapp.databinding.RuleListItemBinding

class RulesRecyclerAdapter(
    var rules: List<RuleListItem>,
    private val mOnRuleTitleListener: OnRuleTitleListener
) : RecyclerView.Adapter<RulesRecyclerAdapter.RulesViewHolder>() {

    class RulesViewHolder(val binding: RuleListItemBinding, private val onRuleTitleListener: OnRuleTitleListener) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.ruleTitle.setOnClickListener(this)
            binding.arrowFolded.setOnClickListener(this)
            binding.arrowExpanded.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onRuleTitleListener.onRuleClick(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulesViewHolder {
        val view =
            RuleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RulesViewHolder(view, mOnRuleTitleListener)
    }

    override fun onBindViewHolder(holder: RulesViewHolder, position: Int) {
        val rule = rules[position].rule
        val isExpanded = rules[position].isExpanded

        holder.binding.apply {
            ruleTitle.text = rule.title
            ruleContent.text = rule.content
            if (isExpanded) {
                ruleContentContainer.visibility = View.VISIBLE
                arrowFolded.visibility = View.GONE
                arrowExpanded.visibility = View.VISIBLE
            } else {
                ruleContentContainer.visibility = View.GONE
                arrowFolded.visibility = View.VISIBLE
                arrowExpanded.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = rules.size

    interface OnRuleTitleListener {
        fun onRuleClick(position: Int)
    }
}

