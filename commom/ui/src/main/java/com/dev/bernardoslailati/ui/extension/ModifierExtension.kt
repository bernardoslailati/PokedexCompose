package com.dev.bernardoslailati.ui.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier

fun Modifier.clickableWithoutRipple(
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit
) = run {
    this.then(
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = { onClick() }
        )
    )
}