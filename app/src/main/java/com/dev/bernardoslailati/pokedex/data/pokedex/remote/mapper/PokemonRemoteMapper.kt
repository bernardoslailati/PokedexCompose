package com.dev.bernardoslailati.pokedex.data.pokedex.remote.mapper

import com.dev.bernardoslailati.pokedex.data.pokedex.remote.model.PokemonApiModel
import com.dev.bernardoslailati.pokedex.data.pokedex.remote.model.StatApiModel
import com.dev.bernardoslailati.pokedex.data.pokedex.remote.model.StatsApiModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.extension.orZero
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonStatModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonStatsModel

fun PokemonApiModel.toDomain(): PokemonModel =
    PokemonModel(
        id = this.id.orZero(),
        name = this.name.orEmpty(),
        imageUrl = this.sprites?.other?.home?.frontDefault.orEmpty(),
        types = this.types?.mapNotNull { it?.type?.name.orEmpty() }.orEmpty(),
        stats = this.stats?.map { it.toDomain() }.orEmpty(),
    )

fun StatsApiModel?.toDomain(): PokemonStatsModel =
    PokemonStatsModel(
        baseStat = this?.baseStat.orZero(),
        stat = this?.stat.toDomain()
    )

fun StatApiModel?.toDomain(): PokemonStatModel =
    PokemonStatModel(
        name = this?.name.orEmpty()
    )
