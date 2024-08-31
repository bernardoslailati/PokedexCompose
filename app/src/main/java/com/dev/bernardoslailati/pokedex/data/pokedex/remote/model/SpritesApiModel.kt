package com.dev.bernardoslailati.pokedex.data.pokedex.remote.model

import com.dev.bernardoslailati.pokedex.data.pokedex.remote.model.OtherSpritesApiModel
import kotlinx.serialization.Serializable

@Serializable
data class SpritesApiModel(
    val other: OtherSpritesApiModel? = null
)