package com.dev.bernardoslailati.pokedex.data.pokedex.remote.service

import com.dev.bernardoslailati.pokedex.data.pokedex.remote.model.PokemonApiModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PokedexApiService(private val httpClient: HttpClient) {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/pokemon"
        const val TOTAL_FIRST_GENERATION_POKEMONS = 151
    }

    suspend fun fetchPokemon(id: Int) =
        httpClient.get("$BASE_URL/$id").body<PokemonApiModel>()

}