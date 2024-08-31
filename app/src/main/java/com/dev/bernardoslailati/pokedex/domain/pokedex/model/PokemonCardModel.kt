package com.dev.bernardoslailati.pokedex.domain.pokedex.model

import androidx.compose.runtime.Immutable

const val HP_STAT_NAME = "hp"
const val ATTACK_STAT_NAME = "attack"
const val DEFENSE_STAT_NAME = "defense"
const val SPEED_STAT_NAME = "speed"
const val MAX_HP = 300
const val MAX_ATTACK = 300
const val MAX_DEFENSE = 300
const val MAX_SPEED = 300

@Immutable
data class PokemonCardModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val types: List<PokemonType>,
    val stats: List<PokemonStatsModel>
) {
    val hp: Int by lazy {
        stats.firstOrNull { it.stat.name == HP_STAT_NAME }?.baseStat ?: -1
    }
    val attack: Int by lazy {
        stats.firstOrNull { it.stat.name == ATTACK_STAT_NAME }?.baseStat ?: -1
    }
    val defense: Int by lazy {
        stats.firstOrNull { it.stat.name == DEFENSE_STAT_NAME }?.baseStat ?: -1
    }
    val speed: Int by lazy {
        stats.firstOrNull { it.stat.name == SPEED_STAT_NAME }?.baseStat ?: -1
    }

    fun getHpText(): String = " $hp/$MAX_HP"
    fun getAttackText(): String = " $attack/$MAX_ATTACK"
    fun getDefenseText(): String = " $defense/$MAX_DEFENSE"
    fun getSpeedText(): String = " $speed/$MAX_SPEED"
}