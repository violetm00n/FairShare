package com.example.tripsplitter.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tripsplitter.models.Trip
import com.example.tripsplitter.utils.calculateDebts  // Import the calculateDebts function

@Composable
fun BalancesScreen(trip: Trip, onBack: () -> Unit) {
    val debts = calculateDebts(trip.participants)  // Use the function from DebtUtils.kt

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Balance Details", modifier = Modifier.padding(bottom = 16.dp))

        // Show debts between participants
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(debts.size) { index ->
                val debt = debts[index]

                val owesText = if (debt.debtor.balance > 0) {
                    "${debt.debtor.name} owes ${debt.creditor.name}: ₹${"%.2f".format(debt.amount)}"
                } else {
                    "${debt.creditor.name} owes ${debt.debtor.name}: ₹${"%.2f".format(debt.amount)}"
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(owesText)
                }
            }
        }

        Button(onClick = onBack, modifier = Modifier.padding(top = 16.dp)) {
            Text("Back to Trip")
        }
    }
}
