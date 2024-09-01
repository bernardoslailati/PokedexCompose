package com.dev.bernardoslailati.pokedex.feature.pokedex

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.bernardoslailati.pokedex.commom.coroutines.io
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonGeneration
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.repository.PokedexRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class PokedexViewModel(private val repository: PokedexRepository) : ViewModel() {

    var uiState: PokedexUiState by mutableStateOf(PokedexUiState())

    init {
        io.launch {
            repository.fetchPokemons(generation = PokemonGeneration.FIRST)
                .collectLatest { pokemonList ->
                    if (pokemonList.isReadyToShow(generation = PokemonGeneration.FIRST) || pokemonList.size > 5)
                        uiState = uiState.copy(isLoading = false, pokemons = pokemonList)
                }
        }
    }

    fun onEvent(event: PokedexEvent) {
        when (event) {
            PokedexEvent.OnFetchPokemons -> fetchPokemons()
        }
    }

    private fun fetchPokemons() {
        uiState = uiState.copy(isLoading = true, failedToSync = false)
        verifyIfSyncIsFailed()
    }

    private fun verifyIfSyncIsFailed() {
        viewModelScope.launch {
            delay(5_000.milliseconds)
            if (uiState.isLoading)
                uiState = uiState.copy(isLoading = false, failedToSync = true)
        }
    }

    private fun List<PokemonModel>.isReadyToShow(generation: PokemonGeneration): Boolean =
        this.map { it.id }.containsAll(generation.rangeIds().toList())

}