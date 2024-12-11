package com.example.tripsplitter.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tripsplitter.TripSplitterScreen
import com.example.tripsplitter.data.TripManager
import com.example.tripsplitter.models.Trip

@Composable
fun TripSplitterNavigation(navController: NavHostController, tripManager: TripManager) {
    NavHost(navController = navController, startDestination = "tripScreen") {
        composable("tripScreen") {
            TripSplitterScreen(
                context = navController.context,
                onViewBalances = {
                    navController.navigate("balancesScreen")
                },
                tripManager = tripManager
            )
        }
        composable("balancesScreen") {
            val trip = tripManager.getTripData() ?: Trip("", emptyList(), emptyList())
            BalancesScreen(
                trip = trip,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
