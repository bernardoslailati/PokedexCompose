package com.dev.bernardoslailati.pokedex

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.bernardoslailati.pokedex.commom.ui.theme.AppRoute
import com.dev.bernardoslailati.pokedex.commom.ui.theme.PokedexTheme
import com.dev.bernardoslailati.pokedex.feature.pokedex.PokedexEvent
import com.dev.bernardoslailati.pokedex.feature.pokedex.PokedexViewModel
import com.dev.bernardoslailati.pokedex.feature.pokedex.screen.PokedexScreen
import com.dev.bernardoslailati.pokedex.feature.splash.PokedexSplashScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun App() {
    PokedexTheme {
        val navController = rememberNavController()

        val pokedexViewModel = koinViewModel<PokedexViewModel>()

        Surface {
            NavHost(
                navController = navController,
                startDestination = AppRoute.Splash
            ) {
                composable<AppRoute.Splash> {
                    PokedexSplashScreen(
                        onSplashStart = {
                            pokedexViewModel.onEvent(PokedexEvent.OnFetchPokemons)
                        }, onSplashFinished = {
                            navController.navigate(AppRoute.Pokedex) {
                                popUpTo(AppRoute.Splash) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

                composable<AppRoute.Pokedex> {
                    PokedexScreen(uiState = pokedexViewModel.uiState)
                }
            }
        }
    }
}