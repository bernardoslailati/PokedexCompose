package com.dev.bernardoslailati.pokedex.domain.pokedex.extension

fun Int?.orZero() : Int = this ?: 0

fun Boolean?.orFalse() : Boolean = this ?: false