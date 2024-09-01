package com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dev.bernardoslailati.ui.theme.Typography

@Composable
fun PokemonStatInfo(statName: String, color: Color, progress: Float, label: String) {
    Row {
        Text(
            modifier = Modifier.fillMaxWidth(0.2f),
            text = statName.uppercase(),
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = com.dev.bernardoslailati.ui.theme.Typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                shadow = Shadow(offset = Offset(2f, 2f))
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        PokemonStatBar(
            modifier = Modifier.weight(1f),
            progress = progress,
            color = color,
            label = label
        )
    }
}