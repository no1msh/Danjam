package com.saeongjima.designsystem.component.checkbox

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black700
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.White

@Composable
fun DanjamCheckboxWithLabel(
    isChecked: Boolean,
    label: String,
    modifier: Modifier = Modifier,
    size: Float = 24f,
    checkedContainerColor: Color = MainColor,
    uncheckedContainerColor: Color = Black200,
    checkedMarkColor: Color = White,
    uncheckedMarkColor: Color = Black700,
    onValueChange: (Boolean) -> Unit,
) {
    val checkboxColor: Color by animateColorAsState(
        if (isChecked) checkedContainerColor else uncheckedContainerColor,
        label = "checkBoxColorTransition",
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .toggleable(
                value = isChecked,
                role = Role.Checkbox,
                onValueChange = onValueChange,
            )
            .padding(8.dp),
    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(color = checkboxColor, shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painterResource(id = R.drawable.ic_check_24),
                contentDescription = null,
                tint = if (isChecked) checkedMarkColor else uncheckedMarkColor,
                modifier = Modifier.size(24.dp),
            )
        }
        Text(
            text = label,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.titleMedium,
            color = Black700,
        )
    }
}

@Preview
@Composable
fun DanjamCheckBoxWithLabelPreview() {
    DanjamTheme {
        var isChecked by remember { mutableStateOf(true) }
        DanjamCheckboxWithLabel(
            isChecked = isChecked,
            onValueChange = { isChecked = !isChecked },
            label = "개인정보 동의",
        )
    }
}
