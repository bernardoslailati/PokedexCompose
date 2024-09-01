package com.dev.bernardoslailati.pokedex.feature.pokedex

import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel

data class PokedexUiState(
    val isLoading: Boolean = false,
    val failedToSync: Boolean = false,
    val pokemons: List<PokemonModel> = emptyList(),
)