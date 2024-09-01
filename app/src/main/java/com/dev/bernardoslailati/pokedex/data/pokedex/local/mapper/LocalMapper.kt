package com.dev.bernardoslailati.pokedex.data.pokedex.local.mapper

import com.dev.bernardoslailati.pokedex.data.pokedex.local.model.PokemonLocalModel
import com.dev.bernardoslailati.pokedex.data.pokedex.remote.model.PokemonApiModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.extension.orZero
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonStatModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonStatsModel

fun PokemonApiModel.toLocal(): PokemonLocalModel =
    PokemonLocalModel(
        id = id ?: 0,
        name = name.orEmpty(),
        imageUrl = sprites?.other?.home?.frontDefault.orEmpty(),
        types = types?.joinToString { it?.type?.name.orEmpty() }.orEmpty(),
        stats = stats.orEmpty().joinToString { it?.baseStat.toString() + ";" + it?.stat?.name }
    )

fun PokemonLocalModel.toDomain(): PokemonModel =
    PokemonModel(
        id = id,
        name = name,
        imageUrl = imageUrl,
        isFavorite = isFavorite,
        types = types.split(", "),
        stats = stats.split(", ").map { statInfo ->
            val stat = statInfo.split(";")
            PokemonStatsModel(
                baseStat = stat.firstOrNull()?.toIntOrNull().orZero(),
                stat = PokemonStatModel(name = stat.lastOrNull().orEmpty())
            )
        }
    )