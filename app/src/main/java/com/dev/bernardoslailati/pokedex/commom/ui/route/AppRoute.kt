package com.dev.bernardoslailati.pokedex.commom.ui.route

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {
    @Serializable
    data object Splash : AppRoute

    @Serializable
    data object Pokedex : AppRoute
}
