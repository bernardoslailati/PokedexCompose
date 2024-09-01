package com.dev.bernardoslailati.pokedex.domain.pokedex.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class PokemonModel(
    val id: Int,
    val name: String,
    val isFavorite: Boolean,
    val imageUrl: String,
    val types: List<String>,
    val stats: List<PokemonStatsModel>,
)
