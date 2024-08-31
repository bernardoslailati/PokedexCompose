package com.dev.bernardoslailati.pokedex.data.pokedex.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dev.bernardoslailati.pokedex.data.pokedex.local.model.PokemonLocalModel

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getAll(): List<PokemonLocalModel>

    @Query("SELECT * FROM pokemon WHERE id IN (:pokemonIds)")
    fun loadAllByIds(pokemonIds: IntArray): List<PokemonLocalModel>

    @Query("SELECT * FROM pokemon WHERE id = :id LIMIT 1")
    fun findById(id: Int): PokemonLocalModel?

    @Query("SELECT * FROM pokemon WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): PokemonLocalModel?

    @Insert
    fun insert(pokemon: PokemonLocalModel)

    @Insert
    fun insertAll(vararg pokemons: PokemonLocalModel)

    @Update
    fun update(pokemon: PokemonLocalModel)

    @Delete
    fun delete(pokemon: PokemonLocalModel)

    @Query("DELETE FROM pokemon")
    fun deleteAll()

}