package com.dev.bernardoslailati.pokedex.data.pokedex.remote.repository

import android.util.Log
import com.dev.bernardoslailati.pokedex.data.pokedex.local.datasource.PokedexLocalDataSource
import com.dev.bernardoslailati.pokedex.data.pokedex.local.mapper.toLocal
import com.dev.bernardoslailati.pokedex.data.pokedex.local.model.PokemonLocalModel
import com.dev.bernardoslailati.pokedex.data.pokedex.remote.datasource.PokedexRemoteDataSource
import com.dev.bernardoslailati.pokedex.data.sync.local.datasource.SyncLocalDataSource
import com.dev.bernardoslailati.pokedex.domain.pokedex.mapper.toDomainList
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonGeneration
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.repository.PokedexRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class PokedexRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val remoteDataSource: PokedexRemoteDataSource,
    private val localDataSource: PokedexLocalDataSource,
    private val syncLocalDataSource: SyncLocalDataSource
) : PokedexRepository {

    override suspend fun fetchPokemons(generation: PokemonGeneration): Flow<List<PokemonModel>> =
        flow {
            var localPokemonsList = mutableListOf<PokemonLocalModel>()

            while (localPokemonsList.size != generation.rangeIds().toList().size) {
                localPokemonsList = withContext(ioDispatcher) {
                    localDataSource.getPokemons().toMutableList()
                }
                Log.d("OPAOPA", "localPokemons ${localPokemonsList.size}")

                val localPokemonIds = localPokemonsList.map { it.id }.toSet()
                Log.d("OPAOPA", "localPokemonIds $localPokemonIds")

                val remainingPokemonIds =
                    generation.rangeIds().toList().subtract(localPokemonIds)
                if (remainingPokemonIds.isEmpty())
                    syncLocalDataSource.confirmPokemonsSync(generation = PokemonGeneration.FIRST)
                Log.d("OPAOPA", "remainingPokemonIds $remainingPokemonIds")

                if (localPokemonsList.isNotEmpty())
                    emit(localPokemonsList.toDomainList())

                if (remainingPokemonIds.isNotEmpty()) {
                    loop@ for (remainingPokemonId in remainingPokemonIds) {
                        val remotePokemon =
                            withContext(ioDispatcher) {
                                remoteDataSource.fetchPokemon(
                                    remainingPokemonId
                                )
                            } ?: break@loop

                        val localPokemon = remotePokemon.toLocal()
                        localPokemonsList.add(localPokemon)

                        Log.d("OPAOPA", "emit [${localPokemon.id}] $localPokemon")
                        withContext(ioDispatcher) { localDataSource.savePokemon(localPokemon) }

                        if (localPokemonsList.isNotEmpty()) emit(localPokemonsList.toDomainList())
                    }
                }
            }

            delay(timeMillis = 2_000)
        }
}