package com.dev.bernardoslailati.pokedex.feature.pokedex

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonGeneration
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.repository.PokedexRepository
import com.dev.bernardoslailati.pokedex.feature.pokedex.PokedexEvent.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class PokedexViewModel(private val repository: PokedexRepository) : ViewModel() {

    var uiState: PokedexUiState by mutableStateOf(PokedexUiState())

    init {
        viewModelScope.launch {
            repository.fetchPokemons(generation = PokemonGeneration.FIRST)
                .collect { pokemonList ->
                    if (pokemonList.isReadyToShow(generation = PokemonGeneration.FIRST))
                        uiState = uiState.copy(isLoading = false, pokemons = pokemonList)
                }
        }
    }

    fun onEvent(event: PokedexEvent) {
        when (event) {
            OnFetchPokemons -> fetchPokemons()
            is OnFavoriteChange -> favoriteChange(pokemon = event.pokemon)
        }
    }

    private fun fetchPokemons() {
        uiState = uiState.copy(isLoading = true, failedToSync = false)
        viewModelScope.launch {
            repository.syncPokemons(generation = PokemonGeneration.FIRST)
        }
        verifyIfSyncIsFailed()
    }

    private fun favoriteChange(pokemon: PokemonModel) {
        viewModelScope.launch {
            repository.favoriteChange(pokemon)
        }
    }

    private fun verifyIfSyncIsFailed() {
        viewModelScope.launch {
            delay(VERIFY_IF_SYNC_IS_FAILED_TIMEOUT.milliseconds)
            if (uiState.isLoading)
                uiState = uiState.copy(isLoading = false, failedToSync = true)
        }
    }

    private fun List<PokemonModel>.isReadyToShow(generation: PokemonGeneration): Boolean {
        val pokemonIdsList = this.map { it.id }
        val generationIdsList = generation.rangeIds().toList()

        return pokemonIdsList.containsAll(generationIdsList) || pokemonIdsList.containsAll(
            generationIdsList.take(5)
        )
    }

    companion object {
        const val VERIFY_IF_SYNC_IS_FAILED_TIMEOUT = 5_000
    }
}