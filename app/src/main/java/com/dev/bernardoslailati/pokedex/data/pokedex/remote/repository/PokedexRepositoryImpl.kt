package com.dev.bernardoslailati.pokedex.data.pokedex.remote.repository

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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PokedexRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val remoteDataSource: PokedexRemoteDataSource,
    private val localDataSource: PokedexLocalDataSource,
    private val syncLocalDataSource: SyncLocalDataSource
) : PokedexRepository {

    override suspend fun syncPokemons(generation: PokemonGeneration) {
        withContext(ioDispatcher) {
            var localPokemonsList = mutableListOf<PokemonLocalModel>()
            val generationPokemonIdsList = generation.rangeIds().toList()

            loopWhile@ while (localPokemonsList.size != generationPokemonIdsList.size) {
                localPokemonsList = localDataSource.getPokemons().toMutableList()

                val localPokemonIds = localPokemonsList.map { it.id }.toSet()

                val remainingPokemonIds =
                    generation.rangeIds().toList().subtract(localPokemonIds)

                if (remainingPokemonIds.isEmpty()) {
                    syncLocalDataSource.confirmPokemonsSync(generation = PokemonGeneration.FIRST)
                    break@loopWhile
                }

                loop@ for (remainingPokemonId in remainingPokemonIds) {
                    val remotePokemon =
                            remoteDataSource.fetchPokemon(
                                remainingPokemonId
                            ) ?: break@loop

                    val localPokemon = remotePokemon.toLocal()
                    localPokemonsList.add(localPokemon)

                    localDataSource.savePokemon(localPokemon)
                }

                if (localPokemonsList.map { it.id }.containsAll(generationPokemonIdsList))
                    syncLocalDataSource.confirmPokemonsSync(generation = PokemonGeneration.FIRST)

                delay(timeMillis = TRY_AGAIN_SYNC_DELAY_TIME)
            }
        }
    }

    override suspend fun fetchPokemons(generation: PokemonGeneration): Flow<List<PokemonModel>> =
        localDataSource.getByGeneration(generation)
            .map {
                it.toDomainList()
            }
            .catch { emit(emptyList()) }
            .flowOn(ioDispatcher)


    override suspend fun favoriteChange(pokemon: PokemonModel) {
        withContext(ioDispatcher) {
            val targetPokemonLocal =
                localDataSource.getPokemonById(pokemon.id)

            targetPokemonLocal?.let {
                localDataSource.updatePokemon(targetPokemonLocal.copy(isFavorite = !targetPokemonLocal.isFavorite))
            }
        }
    }

    companion object {
        const val TRY_AGAIN_SYNC_DELAY_TIME = 2_000L
    }

}