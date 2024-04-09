package com.saeongjima.designsystem.component.checkbox

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black700
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.White

@Composable
fun DanjamCheckbox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    size: Float = 24f,
    checkedContainerColor: Color = MainColor,
    uncheckedContainerColor: Color = Black200,
    checkedMarkColor: Color = White,
    uncheckedMarkColor: Color = Black700,
    onValueChange: (Boolean) -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val checkboxColor: Color by animateColorAsState(
        if (isChecked) checkedContainerColor else uncheckedContainerColor,
        label = "checkBoxColorTransition",
    )

    Box(
        modifier = modifier
            .padding(8.dp)
            .size(size.dp)
            .background(color = checkboxColor, shape = RoundedCornerShape(4.dp))
            .triStateToggleable(
                state = ToggleableState(isChecked),
                onClick = { onValueChange(!isChecked) },
                enabled = true,
                role = Role.Checkbox,
                interactionSource = interactionSource,
                indication = androidx.compose.material.ripple.rememberRipple(
                    bounded = false,
                    radius = 20.dp,
                ),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painterResource(id = R.drawable.ic_check_24),
            contentDescription = null,
            tint = if (isChecked) checkedMarkColor else uncheckedMarkColor,
            modifier = Modifier.size(24.dp),
        )
    }
}

@Preview
@Composable
fun DanjamCheckboxPreview() {
    DanjamTheme {
        var isChecked by remember { mutableStateOf(true) }
        DanjamCheckbox(
            isChecked = isChecked,
            onValueChange = { isChecked = !isChecked },
        )
    }
}
