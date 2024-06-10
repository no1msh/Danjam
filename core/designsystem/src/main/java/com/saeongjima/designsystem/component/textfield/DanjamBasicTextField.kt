package com.saeongjima.designsystem.component.textfield

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.Correct
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.Error
import com.saeongjima.designsystem.theme.MainColor

@Composable
fun DanjamBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String = "",
    hintColor: Color = Black500,
    errorText: String = "",
    isSecure: Boolean = false,
    isReadOnly: Boolean = false,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    hasBorderColor: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    leadingContent: @Composable (RowScope.() -> Unit)? = null,
    trailingContent: @Composable (RowScope.() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val hasFocused by interactionSource.collectIsFocusedAsState()
    val transition = updateTransition(hasFocused)
    val borderColor by transition.animateColor { if (it) Correct else Black200 }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

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
        readOnly = isReadOnly,
        cursorBrush = SolidColor(MainColor),
        interactionSource = interactionSource,
        textStyle = MaterialTheme.typography.titleLarge.copy(Black950),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus(true)
            },
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        enabled = isEnabled,
        singleLine = true,
        visualTransformation = if (isSecure) PasswordVisualTransformation() else VisualTransformation.None
    ) { innerTextField ->
        val modifierWithBorder =
            if (hasFocused && hasBorderColor) {
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                if (leadingContent == null) {
                    Spacer(modifier = Modifier.width(16.dp))
                } else {
                    leadingContent()
                }
                if (!hasFocused && value.isBlank()) {
                    Text(
                        text = if (isError) errorText else hintText,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (isError) Error else hintColor,
                    )
                } else {
                    innerTextField()
                }
            }
            if (trailingContent == null) {
                Spacer(modifier = Modifier.width(16.dp))
            } else {
                trailingContent()
            }
        }

    }
}

@Preview
@Composable
private fun DanjamBasicTextFieldPreview() {
    var value by remember { mutableStateOf("") }

    DanjamTheme {
        DanjamBasicTextField(
            value = value,
            onValueChange = { value = it },
            hintText = "Hint",
            hasBorderColor = false,
            leadingContent = {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 12.dp)
                )
            },
            trailingContent = {
                Button(onClick = { }) {

                }
            }
        )
    }
}
