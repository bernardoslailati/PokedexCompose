package com.dev.bernardoslailati.pokedex.domain.pokedex.mapper

import androidx.compose.runtime.Stable
import com.dev.bernardoslailati.pokedex.data.pokedex.local.mapper.toDomain
import com.dev.bernardoslailati.pokedex.data.pokedex.local.model.PokemonLocalModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonCardModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonType

@Stable
fun PokemonModel.toPresentation(): PokemonCardModel {
    return PokemonCardModel(
        id = "#${id.toString().padStart(3, '0')}",
        name = name,
        imageUrl = imageUrl,
        types = types.map { typeString ->
            PokemonType.fromType(
                type = typeString.replaceFirstChar { type ->
                    if (type.isLowerCase())
                        type.titlecase()
                    else type.toString()
                }
            )
        },
        stats = stats
    )
}

fun List<PokemonLocalModel>.toDomainList(): List<PokemonModel> = this.map { it.toDomain() }