package com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonType

@Composable
fun PokemonTypeFilters(
    modifier: Modifier = Modifier,
    initialSelectedTypes: List<PokemonType> = emptyList(),
    onPokemonTypeFilterSelected: (PokemonType, isSelected: Boolean) -> Unit
) {
    Log.d("OPAOPA", "loading filters... initialSelectedTypes: $initialSelectedTypes")
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(PokemonType.entries.size) { index ->
            val pokemonType = PokemonType.entries[index]
            PokemonTypeChip(
                pokemonType = pokemonType,
                initialIsSelected = initialSelectedTypes.contains(pokemonType),
                onTypeFilterSelected = onPokemonTypeFilterSelected
            )
        }
    }
}