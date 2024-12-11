package com.example.tripsplitter.utils

import com.example.tripsplitter.models.Participant
data class Debt(
    val debtor: Participant,  // Participant who owes money
    val creditor: Participant,  // Participant to whom money is owed
    val amount: Double  // Amount owed
)

// This function calculates the debts between participants based on their balances
fun calculateDebts(participants: List<Participant>): List<Debt> {
    val creditors = participants.filter { it.balance > 0 }.toMutableList()  // Those who are owed money
    val debtors = participants.filter { it.balance < 0 }.toMutableList()  // Those who owe money

    val debts = mutableListOf<Debt>()

    // Match debtors and creditors
    var i = 0
    var j = 0
    while (i < debtors.size && j < creditors.size) {
        val debtor = debtors[i]
        val creditor = creditors[j]

        val debtAmount = Math.min(Math.abs(debtor.balance), creditor.balance)
        debts.add(Debt(debtor, creditor, debtAmount))

        // Update balances
        debtors[i] = debtor.copy(balance = debtor.balance + debtAmount)
        creditors[j] = creditor.copy(balance = creditor.balance - debtAmount)

        // Move to the next debtor or creditor if balance is cleared
        if (debtor.balance == 0.0) {
            i++
        }
        if (creditor.balance == 0.0) {
            j++
        }
    }

    return debts
}


