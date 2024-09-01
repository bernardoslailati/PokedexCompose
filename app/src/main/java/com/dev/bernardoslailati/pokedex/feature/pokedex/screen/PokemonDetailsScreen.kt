package com.dev.bernardoslailati.pokedex.feature.pokedex.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.dev.bernardoslailati.pokedex.R
import com.dev.bernardoslailati.pokedex.commom.ui.theme.Typography
import com.dev.bernardoslailati.pokedex.domain.pokedex.extension.orZero
import com.dev.bernardoslailati.pokedex.domain.pokedex.model.PokemonCardModel
import com.dev.bernardoslailati.pokedex.feature.pokedex.screen.component.PokemonStatsInfo
import okhttp3.internal.toImmutableList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonDetailsScreen(
    modifier: Modifier = Modifier,
    pokemon: PokemonCardModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(Color.Black),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        with(sharedTransitionScope) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Black)
            ) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .zIndex(1f)
                        .padding(vertical = 36.dp, horizontal = 16.dp),
                    onClick = onBack
                ) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }

                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .sharedBounds(
                            rememberSharedContentState(
                                key =
                                "${pokemon.id}/${pokemon.types.firstOrNull()}/background"
                            ),
                            enter = scaleIn(),
                            exit = scaleOut(),
                            animatedVisibilityScope = animatedVisibilityScope,
                            clipInOverlayDuringTransition = OverlayClip(
                                RoundedCornerShape(16.dp)
                            )
                        ),
                    painter = painterResource(
                        id = pokemon.types.firstOrNull()?.background.orZero()
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Pokemon type background"
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .padding(vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .sharedBounds(
                                rememberSharedContentState(key = "${pokemon.id}/id"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                        text = pokemon.id,
                        textAlign = TextAlign.Center,
                        style = TextStyle.Default.copy(
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Default,
                            color = Color.White,
                            shadow = Shadow(offset = Offset(2f, 2f))
                        )
                    )

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .sharedBounds(
                                rememberSharedContentState(key = "${pokemon.id}/image"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                        model = pokemon.imageUrl,
                        placeholder = painterResource(id = R.drawable.img_shadow),
                        error = painterResource(id = R.drawable.img_shadow),
                        contentDescription = "Pokemon Image"
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .sharedBounds(
                                rememberSharedContentState(key = "${pokemon.id}/name"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                        text = pokemon.name.uppercase(),
                        textAlign = TextAlign.Center,
                        style = TextStyle.Default.copy(
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Default,
                            color = Color.White,
                            shadow = Shadow(offset = Offset(2f, 2f))
                        )
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        pokemon.types.toImmutableList().forEach {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    modifier = Modifier.size(48.dp),
                                    painter = painterResource(id = it.icon),
                                    contentDescription = "Icon Type ${it.name}"
                                )
                                Text(
                                    text = it.name.uppercase(),
                                    color = Color.White,
                                    style = Typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        shadow = Shadow(offset = Offset(2f, 2f))
                                    )
                                )
                            }
                        }
                    }

                    PokemonStatsInfo(pokemon = pokemon)
                }
            }
        }
    }
}