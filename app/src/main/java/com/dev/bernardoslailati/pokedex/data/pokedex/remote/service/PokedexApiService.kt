package com.dev.bernardoslailati.pokedex.data.pokedex.remote.service

import com.dev.bernardoslailati.pokedex.data.pokedex.remote.model.PokemonApiModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.*
import io.ktor.util.network.UnresolvedAddressException

class PokedexApiService(private val httpClient: HttpClient) {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/pokemon"
        const val TOTAL_FIRST_GENERATION_POKEMONS = 151
    }

    suspend fun fetchPokemon(id: Int): PokemonApiModel? = try {
        httpClient.get("$BASE_URL/$id").body<PokemonApiModel>()
    } catch (e: UnresolvedAddressException) {
        null
    } catch (e: ResponseException) {
        null
    }

}