package com.saeongjima.designsystem.component.textfield

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black800
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.Correct
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor

@Composable
fun DropdownTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    isExpanded: Boolean,
    hintText: String = ""
) {
    val transition = updateTransition(isExpanded)
    val borderColor by transition.animateColor { if (it) Correct else Black200 }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f
    )

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .background(
                color = Black200,
                shape = RoundedCornerShape(4.dp),
            )
            .height(48.dp)
            .fillMaxWidth(),
        readOnly = true,
        cursorBrush = SolidColor(MainColor),
        textStyle = MaterialTheme.typography.titleLarge.copy(Black950),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),

        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus(true)
            },
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        enabled = false,
        singleLine = true,
    ) { innerTextField ->
        val modifierWithBorder =
            if (isExpanded) {
                Modifier.border(1.dp, borderColor, RoundedCornerShape(4.dp))
            } else {
                Modifier
            }

        Row(
            modifier = modifierWithBorder
                .background(
                    color = Black200,
                    shape = RoundedCornerShape(4.dp),
                )
                .focusRequester(focusRequester),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(16.dp))
                if (value.isBlank()) {
                    Text(
                        text = hintText,
                        style = MaterialTheme.typography.titleMedium,
                        color = Black500,
                    )
                } else {
                    innerTextField()
                }
            }
            IconButton(
                onClick = {},
                modifier = Modifier.padding(end = 10.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_drop_down_24),
                    contentDescription = null,
                    tint = Black800,
                    modifier = Modifier.rotate(rotation)
                )
            }
        }
    }
}

@Preview
@Composable
private fun DropdownTextFieldPreview() {
    DanjamTheme {
        DropdownTextField(onValueChange = {}, isExpanded = true)
    }
}
