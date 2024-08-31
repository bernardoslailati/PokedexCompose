package com.dev.bernardoslailati.pokedex.feature.pokedex

sealed interface PokedexEvent {

    data object OnFetchPokemons : PokedexEvent

}