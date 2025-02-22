package com.dev.bernardoslailati.pokedex.data.pokedex.remote.service

import com.dev.bernardoslailati.pokedex.data.pokedex.remote.model.PokemonApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import java.net.ConnectException

class PokedexApiService(private val httpClient: HttpClient) {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/pokemon"
    }

    suspend fun fetchPokemon(id: Int): PokemonApiModel? = try {
        httpClient.get("$BASE_URL/$id").body<PokemonApiModel>()
    } catch (_: UnresolvedAddressException) {
        null
    } catch (_: ResponseException) {
        null
    } catch (_: ConnectException) {
        null
    } catch (_: Exception) {
        null
    }

}