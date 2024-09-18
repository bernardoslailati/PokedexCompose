package com.dev.bernardoslailati.pokedex.feature.pokedex.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dev.bernardoslailati.pokedex.R
import com.dev.bernardoslailati.pokedex.domain.pokedex.mapper.toPresentation
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonType
import com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component.PokemonCard
import com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component.PokemonSearchBar
import com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component.PokemonTypeFilters
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    pokemons: List<PokemonModel>,
    onPokemonClick: (PokemonModel) -> Unit,
    onFavoriteClick: (PokemonModel) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(horizontal = 8.dp),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = pokemons, key = { it.id }) { pokemon ->
            PokemonCard(
                pokemon = pokemon.toPresentation(),
                onPokemonClick = {
                    onPokemonClick(pokemon)
                },
                onFavoriteClick = {
                    if (!pokemon.isFavorite)
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(0)
                        }
                    onFavoriteClick(pokemon)
                },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}