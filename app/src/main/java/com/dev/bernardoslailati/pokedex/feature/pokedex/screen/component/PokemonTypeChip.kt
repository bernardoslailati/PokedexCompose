package com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonType

@Composable
fun PokemonTypeChip(
    modifier: Modifier = Modifier,
    initialIsSelected: Boolean = false,
    pokemonType: PokemonType,
    onTypeFilterSelected: (PokemonType, isSelected: Boolean) -> Unit
) {
    var isSelected by rememberSaveable { mutableStateOf(initialIsSelected) }

    FilterChip(
        selected = isSelected,
        onClick = {
            isSelected = !isSelected
            onTypeFilterSelected(pokemonType, isSelected)
        },
        label = { Text(text = pokemonType.type) },
        modifier = modifier,
        colors = FilterChipDefaults.filterChipColors(
            labelColor = Color.White,
            disabledContainerColor = Color.Transparent,
            selectedContainerColor = pokemonType.color
        ),
        leadingIcon = {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = pokemonType.icon),
                contentDescription = null
            )
        }
    )

}

@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun PokemonTypeChipPreview() {
    PokemonTypeChip(
        modifier = Modifier,
        pokemonType = PokemonType.ICE,
        onTypeFilterSelected = { _, _ -> }
    )
}