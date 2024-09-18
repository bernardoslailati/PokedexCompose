package com.dev.bernardoslailati.pokedex.feature.pokedex

import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel

data class PokedexUiState(
    val isLoading: Boolean = false,
    val failedToSync: Boolean = false,
    val pokemons: List<PokemonModel> = emptyList(),
    val searchParameters: PokemonSearchParameters = PokemonSearchParameters()
) {
    data class PokemonSearchParameters(
        val searchText: String = "",
        val types: List<String> = emptyList()
    )
}