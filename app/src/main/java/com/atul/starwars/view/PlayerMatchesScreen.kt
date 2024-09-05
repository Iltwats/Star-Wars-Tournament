package com.atul.starwars.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atul.starwars.models.Match
import com.atul.starwars.models.Player

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerMatchesScreen(
    playerId: Int,
    matches: List<Match>,
    players: List<Player>,
    onBackClick: () -> Unit
) {
    val playerMatches = matches.filter { it.player1.id == playerId || it.player2.id == playerId }

    val sortedMatches = playerMatches.sortedByDescending { it.match }
    val playerName = getPlayerNameById(playerId, players)

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = playerName) }, navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        })
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Matches",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )

            LazyColumn {
                items(items = sortedMatches, key = { it.match }) { match ->
                    MatchRow(
                        playerId = playerId,
                        match = match,
                        players = players,
                        playerName = playerName
                    )

                    // Separator
                    Divider(
                        modifier = Modifier.padding(horizontal = 2.dp),
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                }
            }
        }
    }


}

@Composable
fun MatchRow(playerId: Int, match: Match, players: List<Player>, playerName: String) {
    val isPlayer1 = match.player1.id == playerId
    val playerScore = if (isPlayer1) match.player1 else match.player2
    val opponentScore = if (isPlayer1) match.player2 else match.player1

    val backgroundColor = when {
        playerScore.score > opponentScore.score -> Color.Green // Win
        playerScore.score < opponentScore.score -> Color.Red   // Loss
        else -> Color.White                                   // Draw
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        val opponentName = players.find { it.id == opponentScore.id }?.name ?: "Unknown"
         Text(
            text = if (isPlayer1) playerName else opponentName,
            modifier = Modifier.weight(1f),
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.Black
            )
        )

        Text(
            text = "${opponentScore.score} - ${playerScore.score}",
            modifier = Modifier.weight(1f),
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.Black
            )
        )

        Text(
            text = if (isPlayer1) opponentName else playerName,
            modifier = Modifier.weight(1f),
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.Black
            )
        )
    }
}

fun getPlayerNameById(playerId: Int, players: List<Player>): String {
    return players.find { it.id == playerId }?.name ?: "Unknown Player"
}