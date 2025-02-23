package com.dev.bernardoslailati.pokedex.data.pokedex.remote.datasource

import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel

interface PokedexRemoteDataSource {

    suspend fun fetchPokemon(id: Int): PokemonModel?

}

