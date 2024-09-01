package com.dev.bernardoslailati.ui.route

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {

    @Serializable
    data object Pokedex : AppRoute

}
