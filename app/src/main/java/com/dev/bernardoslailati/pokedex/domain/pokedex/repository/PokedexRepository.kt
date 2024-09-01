package com.dev.bernardoslailati.pokedex.domain.pokedex.repository

import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonGeneration
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {

    suspend fun syncPokemons(generation: PokemonGeneration)

    suspend fun fetchPokemons(generation: PokemonGeneration): Flow<List<PokemonModel>>

}