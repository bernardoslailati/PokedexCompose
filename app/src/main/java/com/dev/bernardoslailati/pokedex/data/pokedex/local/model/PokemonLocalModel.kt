package com.dev.bernardoslailati.pokedex.data.pokedex.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonLocalModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val types: String,
    val stats: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)