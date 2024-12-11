package com.example.tripsplitter.models

data class Trip(
    val name: String,
    val participants: List<Participant>,
    val expenses: List<Expense>
)