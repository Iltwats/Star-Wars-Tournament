package com.atul.starwars.repository

import com.atul.starwars.models.Match
import com.atul.starwars.models.Player
import retrofit2.http.GET

interface ApiService {

    @GET("/b/IKQQ")
    suspend fun getPlayers(): List<Player>

    @GET("/b/JNYL")
    suspend fun getMatches(): List<Match>
}