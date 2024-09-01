package com.dev.bernardoslailati.pokedex

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.bernardoslailati.pokedex.feature.pokedex.PokedexViewModel
import com.dev.bernardoslailati.pokedex.feature.pokedex.screen.PokedexScreen
import com.dev.bernardoslailati.ui.route.AppRoute
import com.dev.bernardoslailati.ui.theme.PokedexTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun App() {
    PokedexTheme {
        val navController = rememberNavController()

        val pokedexViewModel = koinViewModel<PokedexViewModel>()

        Scaffold { innerPadding ->
            NavHost(
                modifier = Modifier,
                navController = navController,
                startDestination = AppRoute.Pokedex
            ) {
                composable<AppRoute.Pokedex> {
                    PokedexScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = pokedexViewModel.uiState,
                        onEvent = pokedexViewModel::onEvent
                    )
                }
            }
        }
    }
}