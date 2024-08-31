package com.dev.bernardoslailati.pokedex.domain.pokedex.model

enum class PokemonGeneration(val from: Int, val to: Int) {

    FIRST(from = 1, to = 151);

    fun range(): IntRange = from..to

}