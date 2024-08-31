package com.dev.bernardoslailati.pokedex.feature.pokedex

import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel

data class PokedexUiState(
    val pokemons: List<PokemonModel> = emptyList(),
)