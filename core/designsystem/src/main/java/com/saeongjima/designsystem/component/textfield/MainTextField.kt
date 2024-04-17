package com.saeongjima.designsystem.component.textfield

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.component.button.TrailingButton
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.Correct
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.Error
import com.saeongjima.designsystem.theme.MainColor

@Composable
fun DanjamTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String = "",
    errorText: String = "",
    isSecure: Boolean = false,
    @DrawableRes leadingIcon: Int? = null,
    hasTrailingButton: Boolean = false,
    trailingButtonText: String = "",
    onLeadingIconClick: () -> Unit = {},
    onTrailingButtonClick: () -> Boolean = { false },
) {
    val interactionSource = remember { MutableInteractionSource() }

    val hasFocused by interactionSource.collectIsFocusedAsState()

    var hintMessage by remember {
        mutableStateOf(hintText)
    }
    var hintColor by remember {
        mutableStateOf(Black500)
    }

    val transition = updateTransition(hasFocused)
    val borderColor by transition.animateColor { if (it) Correct else Black200 }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val onTrailingButtonClicked = {
        if (!onTrailingButtonClick()) {
            hintMessage = errorText
            hintColor = Error
        }
        focusManager.clearFocus()
    }


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
        cursorBrush = SolidColor(MainColor),
        interactionSource = interactionSource,
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
        enabled = true,
        singleLine = true,
        visualTransformation = if (isSecure) PasswordVisualTransformation() else VisualTransformation.None
    ) { innerTextField ->
        val modifierWithBorder =
            if (hasFocused) {
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
                leadingIcon?.let {
                    IconButton(
                        onClick = onLeadingIconClick,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 10.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = leadingIcon),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = Black500,
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                }
                if (leadingIcon == null) {
                    Spacer(modifier = Modifier.width(16.dp))
                }
                if (!hasFocused && value.isBlank()) {
                    Text(
                        text = hintMessage,
                        style = MaterialTheme.typography.titleMedium,
                        color = hintColor,
                    )
                } else {
                    innerTextField()
                }
            }
            if (hasTrailingButton) {
                TrailingButton(
                    text = trailingButtonText,
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = onTrailingButtonClicked,
                    enabled = value.isNotEmpty(),
                )
            }
        }
    }
}

@Preview
@Composable
fun TextFieldPreview() {
    DanjamTheme {
        var input by remember {
            mutableStateOf("")
        }

        DanjamTextField(
            value = input,
            onValueChange = { input = it },
            hasTrailingButton = true,
            trailingButtonText = "중복 확인",
            hintText = "Ex) danjam1234@mail.com",
            errorText = "중복된 이메일입니다."
        )
    }
}
