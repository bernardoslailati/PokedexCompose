package com.dev.bernardoslailati.pokedex

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.dev.bernardoslailati.pokedex.commom.di.dataSourceModule
import com.dev.bernardoslailati.pokedex.commom.di.localModule
import com.dev.bernardoslailati.pokedex.commom.di.remoteModule
import com.dev.bernardoslailati.pokedex.commom.di.repositoryModule
import com.dev.bernardoslailati.pokedex.commom.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                remoteModule,
                localModule,
                dataSourceModule,
                repositoryModule,
                viewModelModule
            )
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.03)
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }

}