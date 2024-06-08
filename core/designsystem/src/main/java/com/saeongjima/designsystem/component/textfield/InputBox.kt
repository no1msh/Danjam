package com.saeongjima.designsystem.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black950

@Composable
fun InputBox(
    title: String,
    modifier: Modifier = Modifier,
    description: String = "",
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(bottom = 12.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = Black950,
            )
            if (description.isNotBlank()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.labelLarge,
                    color = Black500,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

        }
        content()
    }
}
