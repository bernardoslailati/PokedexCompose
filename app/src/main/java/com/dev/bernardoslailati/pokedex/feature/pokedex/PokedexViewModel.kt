package com.dev.bernardoslailati.pokedex.feature.pokedex

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonGeneration
import com.dev.bernardoslailati.pokedex.domain.pokedex.repository.PokedexRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PokedexViewModel(private val repository: PokedexRepository) : ViewModel() {

    var uiState: PokedexUiState by mutableStateOf(PokedexUiState())

    fun onEvent(event: PokedexEvent) {
        when (event) {
            PokedexEvent.OnFetchPokemons -> fetchPokemons()
        }
    }

    private fun fetchPokemons() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchPokemons(generation = PokemonGeneration.FIRST).collectLatest {
                uiState = uiState.copy(pokemons = it)
            }
        }
    }

}