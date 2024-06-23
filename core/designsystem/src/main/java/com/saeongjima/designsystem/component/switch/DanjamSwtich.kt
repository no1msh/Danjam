package com.saeongjima.designsystem.component.switch

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black900
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.Error
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.Pink40

private const val switchWidth = 52
private const val switchHeight = 32
private const val thumbSize = 24
private const val thumbPadding = 2
private const val thumbOffset = 10

@Composable
fun DanjamSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    checkedTrackColor: Color = MainColor,
    uncheckedTrackColor: Color = Black200,
    thumbColor: Color = Black900,
) {
    val animateThumbOffset by animateDpAsState(
        targetValue = if (checked) thumbOffset.dp else -thumbOffset.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    Box(
        modifier = Modifier
            .size(switchWidth.dp, switchHeight.dp)
            .clip(RoundedCornerShape(switchHeight.dp / 2))
            .clickable {
                onCheckedChange(!checked)
            },
        contentAlignment = Alignment.Center
    ) {
        // Track
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (checked) checkedTrackColor else uncheckedTrackColor,
                    shape = RoundedCornerShape(switchHeight.dp / 2)
                )
        )
        // Thumb
        Box(
            modifier = Modifier
                .size(thumbSize.dp)
                .offset(x = animateThumbOffset)
                .background(
                    color = thumbColor,
                    shape = CircleShape
                )
                .padding(thumbPadding.dp)
        )
    }
}

@Preview
@Composable
fun DanjamSwitchPreviewChecked() {
    DanjamTheme {
        var switchState by remember { mutableStateOf(true) }
        DanjamSwitch(
            checked = switchState,
            onCheckedChange = { switchState = it }
        )
    }
}

@Preview
@Composable
fun DanjamSwitchPreviewNotChecked() {
    DanjamTheme {
        var switchState by remember { mutableStateOf(false) }
        DanjamSwitch(
            checked = switchState,
            onCheckedChange = { switchState = it }
        )
    }
}

@Preview
@Composable
fun DanjamSwitchPreviewDifferentColor() {
    DanjamTheme {
        var switchState by remember { mutableStateOf(true) }
        DanjamSwitch(
            checked = switchState,
            checkedTrackColor = Error,
            thumbColor = Pink40,
            onCheckedChange = { switchState = it }
        )
    }
}