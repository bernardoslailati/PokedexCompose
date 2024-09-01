package com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.ATTACK_STAT_NAME
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.DEFENSE_STAT_NAME
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.HP_STAT_NAME
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonCardModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.SPEED_STAT_NAME

@Composable
fun PokemonStatsInfo(modifier: Modifier = Modifier, pokemon: PokemonCardModel) {
    val hpStatColor = Color(0xFF3DDC84)
    val attackStatColor = Color(0xFFF7665E)
    val defenseStatColor = Color(0xFF65BCDA)
    val speedStatColor = Color(0xFFFF9800)

    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PokemonStatInfo(
            statName = HP_STAT_NAME,
            color = hpStatColor,
            progress = pokemon.hp / 100.toFloat(),
            label = pokemon.getHpText()
        )
        PokemonStatInfo(
            statName = ATTACK_STAT_NAME,
            color = attackStatColor,
            progress = pokemon.attack / 100.toFloat(),
            label = pokemon.getAttackText()
        )
        PokemonStatInfo(
            statName = DEFENSE_STAT_NAME,
            color = defenseStatColor,
            progress = pokemon.defense / 100.toFloat(),
            label = pokemon.getDefenseText()
        )
        PokemonStatInfo(
            statName = SPEED_STAT_NAME,
            color = speedStatColor,
            progress = pokemon.speed / 100.toFloat(),
            label = pokemon.getSpeedText()
        )
    }
}