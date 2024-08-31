package com.dev.bernardoslailati.pokedex.data.pokedex.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypesApiModel(
    val type: TypeApiModel? = null
)