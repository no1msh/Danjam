package com.saeongjima.designsystem.component.switch

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black900
import com.saeongjima.designsystem.theme.DanjamTheme

@Composable
fun DanjamSwitch(
    checked: Boolean,
    checkedTrackColor: Color = MaterialTheme.colorScheme.primary,
    uncheckedTrackColor: Color = Black200,
    thumbColor: Color = Black900,
    onCheckedChange: ((Boolean) -> Unit)? = null
) {
    var isChecked by remember { mutableStateOf(checked) }

    Switch(
        checked = isChecked,
        onCheckedChange = {
            isChecked = it
            onCheckedChange?.invoke(it)
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = thumbColor,
            uncheckedThumbColor = thumbColor,
            checkedTrackColor = checkedTrackColor,
            uncheckedTrackColor = uncheckedTrackColor,
            uncheckedBorderColor = uncheckedTrackColor,
        )
    )
}

@Preview
@Composable
private fun DanjamSwitchPreviewChecked() {
    DanjamTheme {
        DanjamSwitch(
            checked = true,
            onCheckedChange = { /* 스위치 전환시 이벤트를 구현 합니다. */ }
        )
    }
}

@Preview
@Composable
private fun DanjamSwitchPreviewNotChecked() {
    DanjamTheme {
        DanjamSwitch(
            checked = false,
            onCheckedChange = { /* 스위치 전환시 이벤트를 구현 합니다. */ }
        )
    }
}