package com.saeongjima.designsystem.component.textfield

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black800
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.Correct
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor

@Composable
fun SecureTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String = "",
    onInputFinish: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }

    val hasFocused by interactionSource.collectIsFocusedAsState()

    val transition = updateTransition(hasFocused)
    val borderColor by transition.animateColor { if (it) Correct else Black200 }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var isVisible by rememberSaveable {
        mutableStateOf(false)
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
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Go
        ),

        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus(true)
            },
            onGo = {
                onInputFinish()
            }
        ),
        enabled = true,
        singleLine = true,
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                if (!hasFocused && value.isBlank()) {
                    Text(
                        text = hintText,
                        style = MaterialTheme.typography.titleMedium,
                        color = Black500,
                    )
                } else {
                    innerTextField()
                }
            }

            if (hasFocused) {
                IconButton(onClick = { isVisible = !isVisible }) {
                    if (isVisible) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_visible_24),
                            contentDescription = "입력 표시하기",
                            tint = Black800,
                            modifier = Modifier.size(28.dp),
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_invisible_24),
                            contentDescription = "입력 숨기기",
                            tint = Black800,
                            modifier = Modifier.size(28.dp),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SecureTextFieldPreview() {
    DanjamTheme {
        var input by remember {
            mutableStateOf("")
        }

        SecureTextField(
            value = input,
            onValueChange = { input = it },
            hintText = "비밀번호를 입력해주세요",
        )
    }
}
