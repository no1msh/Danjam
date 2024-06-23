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
import com.saeongjima.designsystem.theme.Error
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.Pink40

const val switchWidth = 52
const val switchHeight = 32
const val thumbSize = 24
const val thumbPadding = 2
const val thumbOffset = 10

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
            .size(switchWidth.dp, switchHeight.dp)
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
                    shape = RoundedCornerShape(switchHeight.dp / 2)
                )
        )
        // Thumb
        Box(
            modifier = Modifier
                .size(thumbSize.dp)
                .offset(x = if (isChecked) thumbOffset.dp else -thumbOffset.dp)
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

@Preview
@Composable
private fun DanjamSwitchDifferentColorPreviewChecked() {
    DanjamTheme {
        DanjamSwitch(
            checked = true,
            checkedTrackColor = Error,
            thumbColor = Pink40,
            onCheckedChange = { /* 스위치 전환시 상태 변환 처리 및 추가 작업을 수행합니다. */ }
        )
    }
}