package com.dev.bernardoslailati.pokedex.domain.pokedex.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
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
    @DrawableRes val icon: Int,
    val color: Color,
) {
    NORMAL(
        type = NORMAL_TYPE_NAME,
        background = R.drawable.bg_normal,
        icon = R.drawable.ic_normal,
        color = Color(0xFFA8A77A)
    ),
    FIGHTING(
        type = FIGHTING_TYPE_NAME,
        background = R.drawable.bg_fighting,
        icon = R.drawable.ic_fighting,
        color = Color(0xFFC22E28)
    ),
    WATER(
        type = WATER_TYPE_NAME,
        background = R.drawable.bg_water,
        icon = R.drawable.ic_water,
        color = Color(0xFF6390F0)
    ),
    FIRE(
        type = FIRE_TYPE_NAME,
        background = R.drawable.bg_fire,
        icon = R.drawable.ic_fire,
        color = Color(0xFFFBA54C)
    ),
    GRASS(type = GRASS_TYPE_NAME,
        background = R.drawable.bg_grass,
        icon = R.drawable.ic_grass,
        color = Color(0xFF7AC74C)
    ),
    POISON(
        type = POISON_TYPE_NAME,
        background = R.drawable.bg_poison,
        icon = R.drawable.ic_poison,
        color = Color(0xFFA33EA1)
    ),
    ELECTRIC(
        type = ELECTRIC_TYPE_NAME,
        background = R.drawable.bg_electric,
        icon = R.drawable.ic_electric,
        color = Color(0xFFF2CB55)
    ),
    GROUND(type = GROUND_TYPE_NAME,
        background = R.drawable.bg_ground,
        icon = R.drawable.ic_ground,
        color = Color(0xFFE2BF65)
    ),
    FLYING(
        type = FLYING_TYPE_NAME,
        background = R.drawable.bg_flying,
        icon = R.drawable.ic_flying,
        color = Color(0xFFA98FF3)
    ),
    PSYCHIC(
        type = PSYCHIC_TYPE_NAME,
        background = R.drawable.bg_psychic,
        icon = R.drawable.ic_psychic,
        color = Color(0xFFF366B9)
    ),
    ROCK(
        type = ROCK_TYPE_NAME,
        background = R.drawable.bg_rock,
        icon = R.drawable.ic_rock,
        color = Color(0xFFB6A136)
    ),
    ICE(
        type = ICE_TYPE_NAME,
        background = R.drawable.bg_ice,
        icon = R.drawable.ic_ice,
        color = Color(0xFF96D9D6)
    ),
    BUG(
        type = BUG_TYPE_NAME,
        background = R.drawable.bg_bug,
        icon = R.drawable.ic_bug,
        color = Color(0xFFA6B91A)
    ),
    DRAGON(
        type = DRAGON_TYPE_NAME,
        background = R.drawable.bg_dragon,
        icon = R.drawable.ic_dragon,
        color = Color(0xFF6F35FC)
    ),
    GHOST(
        type = GHOST_TYPE_NAME,
        background = R.drawable.bg_ghost,
        icon = R.drawable.ic_ghost,
        color = Color(0xFF735797)
    ),
    DARK(
        type = DARK_TYPE_NAME,
        background = R.drawable.bg_dark,
        icon = R.drawable.ic_dark,
        color = Color(0xFF705746)
    ),
    STEEL(
        type = STEEL_TYPE_NAME,
        background = R.drawable.bg_steel,
        icon = R.drawable.ic_steel,
        color = Color(0xFFB7B7CE)
    ),
    FAIRY(
        type = FAIRY_TYPE_NAME,
        background = R.drawable.bg_fairy,
        icon = R.drawable.ic_fairy,
        color = Color(0xFFD685AD)
    );

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