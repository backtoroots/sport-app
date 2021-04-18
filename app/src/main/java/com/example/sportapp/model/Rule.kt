package com.example.sportapp.model

data class Rule(val title: String, val content: String)

data class RuleListItem(val rule: Rule, var isExpanded: Boolean)