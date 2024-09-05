package com.atul.starwars.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.atul.starwars.models.Match
import com.atul.starwars.models.Player
import com.atul.starwars.repository.RetrofitInstance

class PlayerViewModel : ViewModel() {

    private val _players = mutableStateOf<List<Player>>(emptyList())
    val players: State<List<Player>> = _players

    private val _matches = mutableStateOf<List<Match>>(emptyList())
    val matches: State<List<Match>> = _matches

    // Function to fetch data from API
    fun fetchData() {
        viewModelScope.launch {
            try {
                val fetchedPlayers = RetrofitInstance.api.getPlayers()
                val fetchedMatches = RetrofitInstance.api.getMatches()

                _players.value = fetchedPlayers
                _matches.value = fetchedMatches

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}