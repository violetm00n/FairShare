package com.example.tripsplitter.models

data class Expense(
    val description: String,
    val amount: Double,
    val paidBy: Participant
)