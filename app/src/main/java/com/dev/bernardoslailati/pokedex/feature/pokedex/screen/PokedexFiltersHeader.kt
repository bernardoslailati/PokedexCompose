package com.dev.bernardoslailati.pokedex.feature.pokedex.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonType
import com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component.PokemonSearchBar
import com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component.PokemonTypeFilters

@Composable
fun PokemonFiltersHeader(
    modifier: Modifier = Modifier,
    initialSearchText: String,
    initialSelectedTypes: List<PokemonType>,
    onSearchPokemons: (String, List<PokemonType>) -> Unit,
    onQueryChange: (String) -> Unit,
    isSearching: Boolean = false,
) {
    var searchText by remember { mutableStateOf(initialSearchText) }
    val selectedPokemonTypeFilters = remember { initialSelectedTypes.toMutableList() }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column {
        PokemonSearchBar(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .focusRequester(focusRequester),
            query = searchText,
            onQueryChange = {
                onQueryChange(it)
                searchText = it
                onSearchPokemons(searchText, selectedPokemonTypeFilters)
                if (it.isEmpty()) {
                    focusManager.clearFocus()
                }
            },
            onSearch = {
                onSearchPokemons(searchText, selectedPokemonTypeFilters)
            },
            active = isSearching
        )
        PokemonTypeFilters(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            initialSelectedTypes = selectedPokemonTypeFilters,
            onPokemonTypeFilterSelected = { pokemonType, isSelected ->
                if (isSelected)
                    selectedPokemonTypeFilters.add(pokemonType)
                else
                    selectedPokemonTypeFilters.remove(pokemonType)

                onSearchPokemons(searchText, selectedPokemonTypeFilters)
            }
        )
    }
}