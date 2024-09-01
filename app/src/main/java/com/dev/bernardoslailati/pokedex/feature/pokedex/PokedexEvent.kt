package com.dev.bernardoslailati.pokedex.feature.pokedex

import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel

sealed interface PokedexEvent {

    data object OnFetchPokemons : PokedexEvent

    data class OnFavoriteChange(val pokemon: PokemonModel) : PokedexEvent

}