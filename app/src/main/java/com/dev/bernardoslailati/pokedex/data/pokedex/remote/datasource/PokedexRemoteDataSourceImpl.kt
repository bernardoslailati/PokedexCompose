package com.dev.bernardoslailati.pokedex.data.pokedex.remote.datasource

import com.dev.bernardoslailati.pokedex.data.pokedex.remote.model.PokemonApiModel
import com.dev.bernardoslailati.pokedex.data.pokedex.remote.service.PokedexApiService

class PokedexRemoteDataSourceImpl(private val api: PokedexApiService) : PokedexRemoteDataSource {

    override suspend fun fetchPokemon(id: Int): PokemonApiModel {
        return api.fetchPokemon(id)
    }

}