package com.dev.bernardoslailati.pokedex.data.sync.local.datasource

import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonGeneration
import kotlinx.coroutines.flow.Flow

interface SyncLocalDataSource {

    suspend fun hasToSyncPokemons(generation: PokemonGeneration): Flow<Boolean>

    suspend fun confirmPokemonsSync(generation: PokemonGeneration)

}