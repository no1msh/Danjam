package com.saeongjima.designsystem.component.dropdownmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black300
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.DanjamTheme

@Composable
fun DanjamDropdownMenuItem(
    value: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = if (isSelected) Black100 else Black300,
            )
            .height(46.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = value,
            style = if (isSelected) MaterialTheme.typography.titleLarge else MaterialTheme.typography.titleMedium,
            color = Black950,
        )
    }
}

@Preview
@Composable
private fun DanjamDropdownMenuItemPreviewSelected() {
    DanjamTheme {
        DanjamDropdownMenuItem(value = "홍익대학교", isSelected = true)
    }
}

@Preview
@Composable
private fun DanjamDropdownMenuItemPreviewNotSelected() {
    DanjamTheme {
        DanjamDropdownMenuItem(value = "홍익대학교", isSelected = false)
    }
}
