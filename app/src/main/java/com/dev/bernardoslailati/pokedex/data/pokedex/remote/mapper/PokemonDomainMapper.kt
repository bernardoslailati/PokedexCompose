package com.dev.bernardoslailati.pokedex.data.pokedex.remote.mapper

import androidx.compose.runtime.Stable
import com.dev.bernardoslailati.pokedex.data.pokedex.local.mapper.toDomain
import com.dev.bernardoslailati.pokedex.data.pokedex.local.model.PokemonLocalModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonCardModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonType

@Stable
fun PokemonModel.toPresentation(): PokemonCardModel {
    return PokemonCardModel(
        id = formatId(id = id),
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
        isFavorite = isFavorite,
        stats = stats
    )
}

private fun formatId(id: Int): String = "#${id.toString().padStart(3, '0')}"

fun List<PokemonLocalModel>.toDomainList(): List<PokemonModel> = this.map { it.toDomain() }