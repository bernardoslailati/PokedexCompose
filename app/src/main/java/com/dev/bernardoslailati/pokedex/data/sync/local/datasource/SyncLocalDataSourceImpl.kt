package com.dev.bernardoslailati.pokedex.data.sync.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.dev.bernardoslailati.pokedex.domain.pokedex.extension.orFalse
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonGeneration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SyncLocalDataSourceImpl(
    private val context: Context
) : SyncLocalDataSource {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pokedex_settings")

    override suspend fun hasToSyncPokemons(generation: PokemonGeneration): Flow<Boolean> {
        val isSyncGeneration1PokemonsKey = booleanPreferencesKey(generation.isSyncGenerationKey)

        return context.dataStore.data.map { preferences ->
            preferences[isSyncGeneration1PokemonsKey].orFalse()
        }
    }

    override suspend fun confirmPokemonsSync(generation: PokemonGeneration) {
        val isSyncGenerationKey = booleanPreferencesKey(generation.isSyncGenerationKey)

        context.dataStore.edit { settings ->
            settings[isSyncGenerationKey] = true
        }
    }

}