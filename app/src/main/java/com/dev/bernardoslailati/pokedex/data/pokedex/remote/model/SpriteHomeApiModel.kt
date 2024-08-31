package com.dev.bernardoslailati.pokedex.data.pokedex.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SpriteHomeApiModel(
    @SerialName("front_default")
    val frontDefault: String? = null
)