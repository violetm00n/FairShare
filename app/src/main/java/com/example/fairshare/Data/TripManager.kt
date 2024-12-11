package com.example.tripsplitter.data

import android.content.Context
import android.content.SharedPreferences
import com.example.tripsplitter.models.Trip
import com.example.tripsplitter.models.Expense
import com.example.tripsplitter.models.Participant
import com.google.gson.Gson

class TripManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("TripData", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Save trip data to SharedPreferences
    fun saveTripData(tripName: String, participants: List<Participant>, expenses: List<Expense>) {
        val tripData = Trip(tripName, participants, expenses)
        val json = gson.toJson(tripData)
        sharedPreferences.edit().putString("tripData", json).apply()
    }

    // Retrieve trip data from SharedPreferences
    fun getTripData(): Trip? {
        val json = sharedPreferences.getString("tripData", null)
        return if (json != null) {
            gson.fromJson(json, Trip::class.java)
        } else {
            null
        }
    }

    // Add an expense and update balances
    fun addExpense(trip: Trip, description: String, amount: Double, paidBy: Participant): Trip {
        val newExpense = Expense(description, amount, paidBy)
        val updatedExpenses = trip.expenses.toMutableList()
        updatedExpenses.add(newExpense)

        // Update participant balances based on who paid
        val totalParticipants = trip.participants.size
        val splitAmount = amount / totalParticipants
        trip.participants.forEach {
            if (it != paidBy) {
                it.balance += splitAmount // they owe the one who paid
            } else {
                it.balance -= (amount - splitAmount) // they are owed the rest
            }
        }

        // Save updated trip data
        saveTripData(trip.name, trip.participants, updatedExpenses)

        // Return updated trip
        return trip.copy(expenses = updatedExpenses)
    }
    fun clearTripData() {
        sharedPreferences.edit().clear().apply()
    }
}
