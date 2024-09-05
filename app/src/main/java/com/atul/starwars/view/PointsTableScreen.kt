package com.atul.starwars.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.atul.starwars.models.Match
import com.atul.starwars.models.Player
import com.atul.starwars.models.PlayerPerformance

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PointsTableScreen(
    players: List<Player>, matches: List<Match>, onPlayerClick: (Int) -> Unit
) {
    val playerPerformances = calculatePlayerPerformances(players, matches)

    val sortedPlayerPerformances =
        playerPerformances.sortedWith(compareByDescending<PlayerPerformance> { it.points }.thenByDescending { it.totalScore })
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Star Wars Blaster Tournament") },
        )
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Points Table",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )

            LazyColumn {
                items(sortedPlayerPerformances) { playerPerformance ->
                    PlayerRow(
                        player = playerPerformance.player,
                        points = playerPerformance.points,
                        totalScore = playerPerformance.totalScore,
                        onPlayerClick = onPlayerClick
                    )
                    // Separator
                    Divider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                }
            }
        }
    }

}

@Composable
fun PlayerRow(
    player: Player, points: Int, totalScore: Int, onPlayerClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onPlayerClick(player.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(player.icon.replace("http://", "https://")),
            contentDescription = player.name,
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = player.name, fontSize = 20.sp, fontWeight = FontWeight.Normal
            )

            Text(
                text = "$totalScore total score", fontSize = 20.sp, fontWeight = FontWeight.Normal
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$points points", fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
        }
    }
}

fun calculatePlayerPerformances(
    players: List<Player>, matches: List<Match>
): List<PlayerPerformance> {
    val playerPerformanceMap = mutableMapOf<Int, PlayerPerformance>()

    players.forEach { player ->
        var totalPoints = 0
        var totalScore = 0

        matches.forEach { match ->
            when (player.id) {
                match.player1.id -> {
                    totalScore += match.player1.score
                    totalPoints += calculatePoints(match.player1.score, match.player2.score)
                }

                match.player2.id -> {
                    totalScore += match.player2.score
                    totalPoints += calculatePoints(match.player2.score, match.player1.score)
                }
            }
        }

        playerPerformanceMap[player.id] = PlayerPerformance(player, totalPoints, totalScore)
    }

    return playerPerformanceMap.values.toList()
}

fun calculatePoints(playerScore: Int, opponentScore: Int): Int {
    return when {
        playerScore > opponentScore -> 3 // Win
        playerScore == opponentScore -> 1 // Draw
        else -> 0 // Loss
    }
}