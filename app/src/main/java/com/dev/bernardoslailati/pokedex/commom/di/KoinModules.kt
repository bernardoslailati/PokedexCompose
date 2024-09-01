package com.dev.bernardoslailati.pokedex.commom.di

import androidx.room.Room
import com.dev.bernardoslailati.pokedex.data.pokedex.local.database.PokedexDatabase
import com.dev.bernardoslailati.pokedex.data.pokedex.local.datasource.PokedexLocalDataSource
import com.dev.bernardoslailati.pokedex.data.pokedex.local.datasource.PokedexLocalDataSourceImpl
import com.dev.bernardoslailati.pokedex.data.pokedex.remote.datasource.PokedexRemoteDataSource
import com.dev.bernardoslailati.pokedex.data.pokedex.remote.datasource.PokedexRemoteDataSourceImpl
import com.dev.bernardoslailati.pokedex.data.pokedex.remote.repository.PokedexRepositoryImpl
import com.dev.bernardoslailati.pokedex.data.pokedex.remote.service.PokedexApiService
import com.dev.bernardoslailati.pokedex.data.sync.local.datasource.SyncLocalDataSource
import com.dev.bernardoslailati.pokedex.data.sync.local.datasource.SyncLocalDataSourceImpl
import com.dev.bernardoslailati.pokedex.domain.pokedex.repository.PokedexRepository
import com.dev.bernardoslailati.pokedex.feature.pokedex.PokedexViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val remoteModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
    single<PokedexApiService> {
        PokedexApiService(httpClient = get())
    }
}

val localModule = module {
    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = PokedexDatabase::class.java,
            name = "pokedex-database"
        ).build()
    }
    single {
        get<PokedexDatabase>().pokemonDao()
    }
}

val coroutinesModule = module {
    single {
        Dispatchers.IO
    }
}

val dataSourceModule = module {
    single<SyncLocalDataSource> { SyncLocalDataSourceImpl(androidApplication()) }
    single<PokedexRemoteDataSource> { PokedexRemoteDataSourceImpl(get()) }
    single<PokedexLocalDataSource> { PokedexLocalDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<PokedexRepository> { PokedexRepositoryImpl(get(), get(), get(), get()) }
}

val viewModelModule = module {
    viewModelOf(::PokedexViewModel)
}