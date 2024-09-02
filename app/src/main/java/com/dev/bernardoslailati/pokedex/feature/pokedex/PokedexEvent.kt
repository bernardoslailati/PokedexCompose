package com.dev.bernardoslailati.pokedex.feature.pokedex

import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonType

sealed interface PokedexEvent {

    data object OnFetchPokemons : PokedexEvent

    data class OnFavoriteChange(val pokemon: PokemonModel) : PokedexEvent

    data class OnSearch(val searchText: String, val types: List<PokemonType>) : PokedexEvent

}