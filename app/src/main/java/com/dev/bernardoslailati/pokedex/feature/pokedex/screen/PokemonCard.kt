package com.dev.bernardoslailati.pokedex.feature.pokedex.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonCardModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    pokemon: PokemonCardModel,
    onPokemonClick: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.95f)
            .clickable { onPokemonClick() },
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        with(sharedTransitionScope) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .sharedBounds(
                            rememberSharedContentState(key = "${pokemon.id}/${pokemon.types.firstOrNull()}/background"),
                            enter = scaleIn(),
                            exit = scaleOut(),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    painter = painterResource(pokemon.types.first().background),
                    contentDescription = "Pokemon Type Background Image",
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(180.dp)
                            .sharedBounds(
                                rememberSharedContentState(key = "${pokemon.id}/image"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                        model = pokemon.imageUrl,
                        contentDescription = "Pokemon Image"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            modifier = Modifier.sharedBounds(
                                rememberSharedContentState(key = "${pokemon.id}/id"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                            text = pokemon.id.padStart(3, '0'),
                            style = TextStyle.Default.copy(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Black,
                                fontFamily = FontFamily.Default,
                                color = Color.White,
                                shadow = Shadow(offset = Offset(2f, 2f))
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            modifier = Modifier.sharedBounds(
                                rememberSharedContentState(key = "${pokemon.id}/name"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                            text = pokemon.name.uppercase(),
                            style = TextStyle.Default.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black,
                                fontFamily = FontFamily.Default,
                                color = Color.White,
                                shadow = Shadow(offset = Offset(2f, 2f))
                            )
                        )
                    }
                }
            }
        }
    }
}