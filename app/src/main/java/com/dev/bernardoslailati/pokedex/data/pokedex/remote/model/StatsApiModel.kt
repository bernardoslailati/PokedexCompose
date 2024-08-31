package com.dev.bernardoslailati.pokedex.data.pokedex.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class StatsApiModel(
    @SerialName(value = "base_stat")
    val baseStat: Int,
    val stat: StatApiModel,
)