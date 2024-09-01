package com.dev.bernardoslailati.pokedex.feature.pokedex.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dev.bernardoslailati.pokedex.R
import com.dev.bernardoslailati.ui.component.PokedexLoadingAnimation
import com.dev.bernardoslailati.pokedex.domain.pokedex.mapper.toPresentation
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import com.dev.bernardoslailati.pokedex.feature.pokedex.PokedexEvent
import com.dev.bernardoslailati.pokedex.feature.pokedex.PokedexEvent.*
import com.dev.bernardoslailati.pokedex.feature.pokedex.PokedexUiState
import com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component.FailedToSyncPokemonsTryAgain

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokedexScreen(
    modifier: Modifier = Modifier,
    uiState: PokedexUiState,
    onEvent: (PokedexEvent) -> Unit
) {
    var selectedPokemon by remember { mutableStateOf<PokemonModel?>(null) }
    var showDetails by remember {
        mutableStateOf(false)
    }
    val lazyListState = rememberLazyListState()

    LaunchedEffect(true) {
        onEvent(OnFetchPokemons)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(1.dp, 1.dp)
                .shadow(elevation = 4.dp),
            painter = painterResource(id = R.drawable.bg_pokedex),
            contentScale = ContentScale.Crop,
            contentDescription = "Pokedex Image"
        )
        if (uiState.failedToSync)
            FailedToSyncPokemonsTryAgain(onTryAgainClick = { onEvent(OnFetchPokemons) })
        else {
            if (uiState.isLoading)
                PokedexLoadingAnimation()
            else {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(Color.Black),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {
                        AnimatedContent(
                            showDetails,
                            label = "Animated Content Pokemon Details"
                        ) { targetState ->
                            if (!targetState) {
                                PokemonListScreen(
                                    modifier = Modifier.padding(vertical = 24.dp),
                                    pokemonList = uiState.pokemons,
                                    lazyListState = lazyListState,
                                    onPokemonClick = { pokemon ->
                                        selectedPokemon = pokemon
                                        showDetails = true
                                    },
                                    onFavoriteClick = { pokemon ->
                                        onEvent(OnFavoriteChange(pokemon))
                                    },
                                    sharedTransitionScope = this@SharedTransitionLayout,
                                    animatedVisibilityScope = this@AnimatedContent
                                )
                            } else {
                                selectedPokemon?.let {
                                    PokemonDetailsScreen(
                                        pokemon = it.toPresentation(),
                                        onBack = { showDetails = false },
                                        sharedTransitionScope = this@SharedTransitionLayout,
                                        animatedVisibilityScope = this@AnimatedContent
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}