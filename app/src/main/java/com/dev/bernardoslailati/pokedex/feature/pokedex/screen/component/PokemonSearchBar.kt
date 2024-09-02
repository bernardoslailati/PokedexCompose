package com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.dev.bernardoslailati.pokedex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit = {}
) {
    SearchBar(
        modifier = modifier,
        placeholder = {
            Text(
                text = "Search for your Pokémon",
                color = Color(0xFFB1B1B1)
            )
        },
        leadingIcon = {
            Image(
                painter = painterResource(R.drawable.ic_pokeball),
                contentDescription = "Pokeball Icon"
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
    ) {}
}