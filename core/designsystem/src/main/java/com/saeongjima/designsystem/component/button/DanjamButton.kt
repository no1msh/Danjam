package com.saeongjima.designsystem.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black300
import com.saeongjima.designsystem.theme.Black600
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.PointColor1

@Composable
fun DanjamButton(
    text: String,
    modifier: Modifier = Modifier,
    size: Int = 50,
    textStyle: TextStyle = MaterialTheme.typography.displayMedium,
    textColor: Color = Black100,
    containerColor: Color = PointColor1,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(size.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonColors(
            containerColor = containerColor,
            contentColor = textColor,
            disabledContainerColor = Black300,
            disabledContentColor = Black600,
        ),
        contentPadding = PaddingValues(0.dp),
        enabled = enabled,
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = textStyle,
                modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
private fun DanjamButtonPreview() {
    DanjamTheme {
        DanjamButton("Hello, World!", enabled = false) {
        }
    }
}
