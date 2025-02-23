package com.dev.bernardoslailati.pokedex.data.pokedex.remote.datasource

import com.dev.bernardoslailati.pokedex.data.pokedex.remote.mapper.toDomain
import com.dev.bernardoslailati.pokedex.data.pokedex.remote.service.PokedexApiService
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel

class PokedexRemoteDataSourceImpl(private val api: PokedexApiService) : PokedexRemoteDataSource {

    override suspend fun fetchPokemon(id: Int): PokemonModel? {
        return api.fetchPokemon(id)?.toDomain()
    }

}