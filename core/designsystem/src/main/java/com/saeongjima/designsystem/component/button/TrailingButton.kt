package com.saeongjima.designsystem.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.White

private const val DefaultWidth = 68
private const val DefaultHeight = 28
private const val DefaultRadius = 4
private const val MinTouchSize = 40

@Composable
fun TrailingButton(
    text: String,
    modifier: Modifier = Modifier,
    width: Int = DefaultWidth,
    height: Int = DefaultHeight,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(DefaultRadius.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MainColor,
                contentColor = White,
                disabledContainerColor = Black500,
                disabledContentColor = White,
            ),
            modifier = Modifier
                .padding(vertical = ((MinTouchSize - height).coerceAtLeast(0) / 2).dp)
                .size(width = width.dp, height = height.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
            )
        }

    }
}

@Preview
@Composable
private fun TrailingButtonPreview() {
    DanjamTheme {
        TrailingButton(text = "중복 확인", onClick = {  }, enabled = true)
    }
}

@Preview
@Composable
private fun DisabledTrailingButtonPreview() {
    DanjamTheme {
        TrailingButton(text = "중복 확인", onClick = {  }, enabled = false)
    }
}
