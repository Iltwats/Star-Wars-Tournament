package com.atul.starwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.atul.starwars.view.PlayerMatchesScreen
import com.atul.starwars.view.PointsTableScreen
import com.atul.starwars.viewmodel.PlayerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: PlayerViewModel = viewModel()
            LaunchedEffect(Unit) {
                viewModel.fetchData()
            }
            AppNavigation(navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController, viewModel: PlayerViewModel) {
    val players = viewModel.players.value
    val matches = viewModel.matches.value

    NavHost(navController = navController, startDestination = "pointsTable") {

        composable("pointsTable") {
            PointsTableScreen(players = players, matches = matches) { playerId ->
                navController.navigate("matches/$playerId")
            }
        }

        composable(
            route = "matches/{playerId}",
            arguments = listOf(navArgument("playerId") { type = NavType.IntType })
        ) { backStackEntry ->
            val playerId = backStackEntry.arguments?.getInt("playerId") ?: 0
            PlayerMatchesScreen(
                playerId = playerId,
                matches = matches,
                players = players,
                onBackClick = {
                    navController.popBackStack()
                })
        }
    }
}
