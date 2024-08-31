package com.dev.bernardoslailati.pokedex.data.pokedex.remote.datasource

import com.dev.bernardoslailati.pokedex.data.pokedex.remote.model.PokemonApiModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface PokedexRemoteDataSource {

    suspend fun fetchPokemon(id: Int): PokemonApiModel

}

