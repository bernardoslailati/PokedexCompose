package com.dev.bernardoslailati.pokedex.feature.pokedex.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.bernardoslailati.pokedex.domain.pokedex.mapper.toPresentation
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonModel
import okhttp3.internal.toImmutableList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    pokemonList: List<PokemonModel>,
    onPokemonClick: (PokemonModel) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(top = 16.dp)
            .padding(horizontal = 8.dp),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = pokemonList.toImmutableList(), key = { it.hashCode() }) { pokemon ->
            PokemonCard(
                pokemon = pokemon.toPresentation(),
                onPokemonClick = {
                    onPokemonClick(pokemon)
                },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}