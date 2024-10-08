package com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dev.bernardoslailati.pokedex.R
import com.dev.bernardoslailati.ui.theme.Typography

@Composable
fun FailedToSyncPokemonsTryAgain(modifier: Modifier = Modifier, onTryAgainClick: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize().padding(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            modifier = Modifier.size(256.dp),
            painter = painterResource(id = R.drawable.ic_sad_pikachu),
            contentDescription = null
        )
        Column {
            Text(
                text = "Oops... Não foi possível sincronizar a sua Pokedéx",
                style = Typography.titleLarge.copy(
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Verifique a sua conexão com internet e tente novamente.",
                style = Typography.bodyLarge.copy(
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light
                )
            )
        }
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(48.dp),
            onClick = onTryAgainClick) {
            Text(
                text = "Tentar novamente",
                style = Typography.titleMedium.copy(color = Color.White)
            )
        }
    }
}