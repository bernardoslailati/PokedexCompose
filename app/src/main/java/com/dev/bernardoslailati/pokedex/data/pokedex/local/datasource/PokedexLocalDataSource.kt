package com.dev.bernardoslailati.pokedex.data.pokedex.local.datasource

import com.dev.bernardoslailati.pokedex.data.pokedex.local.model.PokemonLocalModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonGeneration

interface PokedexLocalDataSource {

    suspend fun getPokemons(): List<PokemonLocalModel>

    suspend fun getByGeneration(generation: PokemonGeneration): List<PokemonLocalModel>

    suspend fun savePokemon(pokemon: PokemonLocalModel)

    suspend fun savePokemons(pokemons: List<PokemonLocalModel>)

    suspend fun getPokemonById(id: Int): PokemonLocalModel?

    suspend fun updatePokemon(pokemon: PokemonLocalModel)

    suspend fun deletePokemon(pokemon: PokemonLocalModel)

    suspend fun deleteAllPokemons()

}