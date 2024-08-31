package com.dev.bernardoslailati.pokedex.data.pokedex.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev.bernardoslailati.pokedex.data.pokedex.local.model.PokemonLocalModel

@Database(entities = [PokemonLocalModel::class], version = 1)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}