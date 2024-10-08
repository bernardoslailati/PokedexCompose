package com.dev.bernardoslailati.pokedex.data.pokedex.local.datasource

import com.dev.bernardoslailati.pokedex.data.pokedex.local.database.PokemonDao
import com.dev.bernardoslailati.pokedex.data.pokedex.local.model.PokemonLocalModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonGeneration
import kotlinx.coroutines.flow.Flow

class PokedexLocalDataSourceImpl(
    private val pokemonDao: PokemonDao
) : PokedexLocalDataSource {

    override suspend fun getPokemons(): List<PokemonLocalModel> {
        return pokemonDao.getAll()
    }

    override suspend fun getByGeneration(generation: PokemonGeneration): Flow<List<PokemonLocalModel>> {
        return pokemonDao.loadAllByIds(generation.rangeIds().toList().toIntArray())
    }

    override suspend fun savePokemon(pokemon: PokemonLocalModel) {
        return pokemonDao.insert(pokemon)
    }

    override suspend fun savePokemons(pokemons: List<PokemonLocalModel>) {
        return pokemonDao.insertAll(*pokemons.toTypedArray())
    }

    override suspend fun getPokemonById(id: Int): PokemonLocalModel? {
        return pokemonDao.findById(id)
    }

    override suspend fun updatePokemon(pokemon: PokemonLocalModel) {
        return pokemonDao.update(pokemon)
    }

    override suspend fun deletePokemon(pokemon: PokemonLocalModel) {
        return pokemonDao.delete(pokemon)
    }

    override suspend fun deleteAllPokemons() {
        return pokemonDao.deleteAll()
    }

}