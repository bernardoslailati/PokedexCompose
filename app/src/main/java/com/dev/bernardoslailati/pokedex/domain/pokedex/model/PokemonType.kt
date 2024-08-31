package com.dev.bernardoslailati.pokedex.domain.pokedex.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.capitalize
import com.dev.bernardoslailati.pokedex.R
import java.util.Locale

private const val NORMAL_TYPE_NAME = "Normal"
private const val FIGHTING_TYPE_NAME = "Fighting"
private const val WATER_TYPE_NAME = "Water"
private const val FIRE_TYPE_NAME = "Fire"
private const val GRASS_TYPE_NAME = "Grass"
private const val POISON_TYPE_NAME = "Poison"
private const val ELECTRIC_TYPE_NAME = "Electric"
private const val GROUND_TYPE_NAME = "Ground"
private const val FLYING_TYPE_NAME = "Flying"
private const val PSYCHIC_TYPE_NAME = "Psychic"
private const val ROCK_TYPE_NAME = "Rock"
private const val ICE_TYPE_NAME = "Ice"
private const val BUG_TYPE_NAME = "Bug"
private const val DRAGON_TYPE_NAME = "Dragon"
private const val GHOST_TYPE_NAME = "Ghost"
private const val DARK_TYPE_NAME = "Dark"
private const val STEEL_TYPE_NAME = "Steel"
private const val FAIRY_TYPE_NAME = "Fairy"

@Immutable
enum class PokemonType(
    val type: String,
    @DrawableRes val background: Int,
    @DrawableRes val icon: Int
) {
    NORMAL(type = NORMAL_TYPE_NAME, background = R.drawable.bg_normal, icon = R.drawable.ic_normal),
    FIGHTING(
        type = FIGHTING_TYPE_NAME,
        background = R.drawable.bg_fighting,
        icon = R.drawable.ic_fighting
    ),
    WATER(type = WATER_TYPE_NAME, background = R.drawable.bg_water, icon = R.drawable.ic_water),
    FIRE(type = FIRE_TYPE_NAME, background = R.drawable.bg_fire, icon = R.drawable.ic_fire),
    GRASS(type = GRASS_TYPE_NAME, background = R.drawable.bg_grass, icon = R.drawable.ic_grass),
    POISON(type = POISON_TYPE_NAME, background = R.drawable.bg_poison, icon = R.drawable.ic_poison),
    ELECTRIC(
        type = ELECTRIC_TYPE_NAME,
        background = R.drawable.bg_electric,
        icon = R.drawable.ic_electric
    ),
    GROUND(type = GROUND_TYPE_NAME, background = R.drawable.bg_ground, icon = R.drawable.ic_grass),
    FLYING(type = FLYING_TYPE_NAME, background = R.drawable.bg_flying, icon = R.drawable.ic_flying),
    PSYCHIC(
        type = PSYCHIC_TYPE_NAME,
        background = R.drawable.bg_psychic,
        icon = R.drawable.ic_psychic
    ),
    ROCK(type = ROCK_TYPE_NAME, background = R.drawable.bg_rock, icon = R.drawable.ic_rock),
    ICE(type = ICE_TYPE_NAME, background = R.drawable.bg_ice, icon = R.drawable.ic_ice),
    BUG(type = BUG_TYPE_NAME, background = R.drawable.bg_bug, icon = R.drawable.ic_bug),
    DRAGON(type = DRAGON_TYPE_NAME, background = R.drawable.bg_dragon, icon = R.drawable.ic_dragon),
    GHOST(type = GHOST_TYPE_NAME, background = R.drawable.bg_ghost, icon = R.drawable.ic_ghost),
    DARK(type = DARK_TYPE_NAME, background = R.drawable.bg_dark, icon = R.drawable.ic_dark),
    STEEL(type = STEEL_TYPE_NAME, background = R.drawable.bg_steel, icon = R.drawable.ic_steel),
    FAIRY(type = FAIRY_TYPE_NAME, background = R.drawable.bg_fairy, icon = R.drawable.ic_fairy);

    companion object {

        fun fromType(type: String): PokemonType {
            return when (type.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }) {
                NORMAL_TYPE_NAME -> NORMAL
                FIGHTING_TYPE_NAME -> FIGHTING
                WATER_TYPE_NAME -> WATER
                FIRE_TYPE_NAME -> FIRE
                GRASS_TYPE_NAME -> GRASS
                POISON_TYPE_NAME -> POISON
                ELECTRIC_TYPE_NAME -> ELECTRIC
                GROUND_TYPE_NAME -> GROUND
                FLYING_TYPE_NAME -> FLYING
                PSYCHIC_TYPE_NAME -> PSYCHIC
                ROCK_TYPE_NAME -> ROCK
                ICE_TYPE_NAME -> ICE
                BUG_TYPE_NAME -> BUG
                DRAGON_TYPE_NAME -> DRAGON
                GHOST_TYPE_NAME -> GHOST
                DARK_TYPE_NAME -> DARK
                STEEL_TYPE_NAME -> STEEL
                FAIRY_TYPE_NAME -> FAIRY
                else -> NORMAL
            }
        }
    }
}