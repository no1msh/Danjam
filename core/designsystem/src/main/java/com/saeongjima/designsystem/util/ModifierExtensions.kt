package com.saeongjima.designsystem.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager

fun Modifier.addFocusCleaner(
    focusManager: FocusManager,
    interactionSource: MutableInteractionSource
): Modifier {
    return this.clickable(
        indication = null,
        interactionSource = interactionSource,
        onClick = { focusManager.clearFocus() }
    )
}
