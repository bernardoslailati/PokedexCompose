package com.dev.bernardoslailati.pokedex.data.pokedex.remote.repository

import android.util.Log
import com.dev.bernardoslailati.pokedex.data.pokedex.local.datasource.PokedexLocalDataSource
import com.dev.bernardoslailati.pokedex.data.pokedex.local.mapper.toLocal
import com.dev.bernardoslailati.pokedex.data.pokedex.remote.datasource.PokedexRemoteDataSource
import com.dev.bernardoslailati.pokedex.domain.pokedex.mapper.toDomainList
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonGeneration
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.repository.PokedexRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class PokedexRepositoryImpl(
    private val remoteDataSource: PokedexRemoteDataSource,
    private val localDataSource: PokedexLocalDataSource
) : PokedexRepository {

    override suspend fun fetchPokemons(generation: PokemonGeneration): Flow<List<PokemonModel>> =
        flow {
            val localPokemons = withContext(Dispatchers.IO) {
                localDataSource.getPokemons().toMutableList()
            }
            Log.d("OPAOPA", "localPokemons ${localPokemons.size}")
            val localPokemonIds = localPokemons.map { it.id }.toSet()
            Log.d("OPAOPA", "localPokemonIds $localPokemonIds")
            val remainingPokemonIds =
                generation.range().toList().subtract(localPokemonIds)
            Log.d("OPAOPA", "remainingPokemonIds $remainingPokemonIds")

            emit(localPokemons.toDomainList())

            if (remainingPokemonIds.isNotEmpty()) {
                for (remainingPokemonId in remainingPokemonIds) {
                    withContext(Dispatchers.IO) {
                        val remotePokemon =
                            remoteDataSource.fetchPokemon(remainingPokemonId)

                        val localPokemon = remotePokemon.toLocal()

                        localPokemons.add(localPokemon)

                        Log.d("OPAOPA", "emit [${localPokemon.id}] $localPokemon")
                        localDataSource.savePokemon(localPokemon)
                    }

                    emit(localPokemons.toDomainList())
                }
            }
        }

}