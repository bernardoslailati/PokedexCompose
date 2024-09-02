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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    pokemons: List<PokemonModel>,
    isSearching: Boolean,
    onPokemonClick: (PokemonModel) -> Unit,
    onFavoriteClick: (PokemonModel) -> Unit,
    onSearchPokemons: (String, List<PokemonType>) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val coroutineScope = rememberCoroutineScope()

    var searchText by rememberSaveable { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column {
        PokemonSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .focusRequester(focusRequester),
            query = searchText,
            onQueryChange = {
                searchText = it
                onSearchPokemons(searchText, emptyList())
                if (it.isEmpty()) {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                    focusManager.clearFocus()
                }
            },
            onSearch = {
                onSearchPokemons(searchText, emptyList())
            },
            active = isSearching
        )
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
                            searchText = ""
                            focusManager.clearFocus()
                            onFavoriteClick(pokemon)
                        },
                        sharedTransitionScope = sharedTransitionScope,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
            }
        }
    }
}