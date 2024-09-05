package com.atul.starwars.models

data class PlayerPerformance(
    val player: Player,
    var points: Int = 0,   // Points from matches
    var totalScore: Int = 0 // Cumulative total score from all matches
)
