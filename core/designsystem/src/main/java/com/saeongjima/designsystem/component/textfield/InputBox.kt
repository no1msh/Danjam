package com.saeongjima.designsystem.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.theme.Black950

@Composable
fun InputBox(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = Black950,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
        )
        content()
    }
}
