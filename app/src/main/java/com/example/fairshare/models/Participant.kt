package com.example.tripsplitter.models

data class Participant(
    val name: String,
    var balance: Double = 0.0,
    val owes: MutableMap<Participant, Double> = mutableMapOf()
)



