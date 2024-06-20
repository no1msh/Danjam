package com.saeongjima.designsystem.component.switch

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black900
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor

private val SWITCH_WIDTH = 52.dp
private val SWITCH_HEIGHT = 32.dp
private val THUMB_SIZE = 24.dp
private val THUMB_PADDING = 2.dp
private val THUMB_OFFSET = 10.dp

@Composable
fun DanjamSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    checkedTrackColor: Color = MainColor,
    uncheckedTrackColor: Color = Black200,
    thumbColor: Color = Black900,
) {
    var isChecked by remember { mutableStateOf(checked) }

    Box(
        modifier = Modifier
            .size(SWITCH_WIDTH, SWITCH_HEIGHT)
            .clickable {
                isChecked = !isChecked
                onCheckedChange(isChecked)
            },
        contentAlignment = Alignment.Center
    ) {
        // Track
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (isChecked) checkedTrackColor else uncheckedTrackColor,
                    shape = RoundedCornerShape(SWITCH_HEIGHT / 2)
                )
        )
        // Thumb
        Box(
            modifier = Modifier
                .size(THUMB_SIZE)
                .offset(x = if (isChecked) THUMB_OFFSET else -THUMB_OFFSET)
                .background(
                    color = thumbColor,
                    shape = CircleShape
                )
                .padding(THUMB_PADDING)
        )
    }
}

@Preview
@Composable
private fun DanjamSwitchPreviewChecked() {
    DanjamTheme {
        DanjamSwitch(
            checked = true,
            onCheckedChange = { /* 스위치 전환시 상태 변환 처리 및 추가 작업을 수행합니다. */ }
        )
    }
}

@Preview
@Composable
private fun DanjamSwitchPreviewNotChecked() {
    DanjamTheme {
        DanjamSwitch(
            checked = false,
            onCheckedChange = { /* 스위치 전환시 상태 변환 처리 및 추가 작업을 수행합니다. */ }
        )
    }
}