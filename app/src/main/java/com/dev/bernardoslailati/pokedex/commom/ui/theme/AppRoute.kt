package com.dev.bernardoslailati.pokedex.commom.ui.theme

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {
    @Serializable
    data object Splash : AppRoute

    @Serializable
    data object Pokedex : AppRoute
}
