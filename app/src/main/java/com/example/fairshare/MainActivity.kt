package com.example.tripsplitter

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tripsplitter.data.TripManager
import com.example.tripsplitter.models.Participant
import com.example.tripsplitter.models.Expense
import com.example.tripsplitter.models.Trip

import android.os.Bundle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tripsplitter.ui.TripSplitterNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tripManager = TripManager(applicationContext)

        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                // Set up the NavController for navigation
                val navController: NavHostController = rememberNavController()

                // Pass the navigation controller to your custom navigation composable
                TripSplitterNavigation(navController = navController, tripManager = tripManager)
            }
        }
    }
}

@Composable
fun TripSplitterScreen(
    context: Context,
    onViewBalances: () -> Unit,
    tripManager: TripManager
) {
    var trip by remember { mutableStateOf<Trip?>(tripManager.getTripData()) }
    var tripName by remember { mutableStateOf("") }
    var participants by remember { mutableStateOf("") }
    var expenseDescription by remember { mutableStateOf("") }
    var expenseAmount by remember { mutableStateOf("") }
    var paidByName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = tripName,
            onValueChange = { tripName = it },
            label = { Text("Enter Title") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        TextField(
            value = participants,
            onValueChange = { participants = it },
            label = { Text("Enter Participants (comma separated)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if (tripName.isNotEmpty() && participants.isNotEmpty()) {
                    val participantList = participants.split(",").map { it.trim() }.map { Participant(it) }
                    trip = Trip(tripName, participantList, emptyList())
                    tripManager.saveTripData(tripName, participantList, emptyList())
                    Toast.makeText(context, "Title'${tripName}' created!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Please enter a title and participants.", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Create")
        }

        Spacer(modifier = Modifier.height(16.dp))

        trip?.let { tripDetails ->
            Text("Trip: ${tripDetails.name}")
            Spacer(modifier = Modifier.height(8.dp))

            Text("Participants:")
            tripDetails.participants.forEach { participant ->
                Text("- ${participant.name}: Balance = ₹${participant.balance}")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = expenseDescription,
                onValueChange = { expenseDescription = it },
                label = { Text("Expense Description") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            TextField(
                value = expenseAmount,
                onValueChange = { expenseAmount = it },
                label = { Text("Expense Amount") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            TextField(
                value = paidByName,
                onValueChange = { paidByName = it },
                label = { Text("Paid By") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            Button(
                onClick = {
                    val paidBy = tripDetails.participants.find { it.name == paidByName }
                    if (paidBy != null && expenseDescription.isNotEmpty() && expenseAmount.isNotEmpty()) {
                        trip = tripManager.addExpense(
                            tripDetails,
                            expenseDescription,
                            expenseAmount.toDouble(),
                            paidBy
                        )
                        Toast.makeText(context, "Expense added!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Invalid input or participant not found.", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Add Expense")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display Expenses
            Text("Expenses:")
            tripDetails.expenses.forEach { expense ->
                Text("${expense.description} - ₹${expense.amount} (Paid by: ${expense.paidBy.name})")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display Balance Summary
            Text("Balance Summary:")
            tripDetails.participants.forEach { participant ->
                Text("${participant.name}: Balance = ₹${participant.balance}")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onViewBalances,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("View Balances")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    try {
                        // Clear trip data
                        tripManager.clearTripData()

                        // Reset all state variables
                        trip = null
                        tripName = ""
                        participants = ""
                        expenseDescription = ""
                        expenseAmount = ""
                        paidByName = ""

                        Toast.makeText(context, "All data has been reset!", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(context, "Error during reset: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("RESET")
            }
        }
    }
}
