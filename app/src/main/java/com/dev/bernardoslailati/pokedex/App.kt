package com.dev.bernardoslailati.pokedex

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.bernardoslailati.pokedex.commom.ui.route.AppRoute
import com.dev.bernardoslailati.pokedex.commom.ui.theme.PokedexTheme
import com.dev.bernardoslailati.pokedex.feature.pokedex.PokedexViewModel
import com.dev.bernardoslailati.pokedex.feature.pokedex.screen.PokedexScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun App() {
    PokedexTheme {
        val navController = rememberNavController()

        val pokedexViewModel = koinViewModel<PokedexViewModel>()

        Surface {
            NavHost(
                navController = navController,
                startDestination = AppRoute.Pokedex
            ) {
                composable<AppRoute.Pokedex> {
                    PokedexScreen(
                        uiState = pokedexViewModel.uiState,
                        onEvent = pokedexViewModel::onEvent
                    )
                }
            }
        }
    }
}