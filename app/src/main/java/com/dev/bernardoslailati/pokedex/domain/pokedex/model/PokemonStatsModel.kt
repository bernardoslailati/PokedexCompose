package com.dev.bernardoslailati.pokedex.domain.pokedex.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonStatsModel(
    val baseStat: Int,
    val stat: PokemonStatModel
)