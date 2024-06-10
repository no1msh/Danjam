package com.saeongjima.designsystem.component.textfield

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.R.string.secure_text_field_input_invisible
import com.saeongjima.designsystem.R.string.secure_text_field_input_visible
import com.saeongjima.designsystem.theme.Black800
import com.saeongjima.designsystem.theme.DanjamTheme

@Composable
fun SecureTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String = "",
    keyboardType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Go,
    keyboardActions: KeyboardActions? = null,
    onInputFinish: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    var isVisible by rememberSaveable {
        mutableStateOf(false)
    }

    DanjamBasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        hintText = hintText,
        keyboardType = keyboardType,
        imeAction = imeAction,
        keyboardActions = keyboardActions
            ?: KeyboardActions(
                onDone = {
                    focusManager.clearFocus(true)
                },
                onGo = {
                    onInputFinish()
                }
            ),
        isSecure = !isVisible,
        trailingContent = {
            IconButton(onClick = { isVisible = !isVisible }) {
                if (isVisible) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_visible_24),
                        contentDescription = stringResource(secure_text_field_input_visible),
                        tint = Black800,
                        modifier = Modifier.size(28.dp),
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_invisible_24),
                        contentDescription = stringResource(secure_text_field_input_invisible),
                        tint = Black800,
                        modifier = Modifier.size(28.dp),
                    )
                }
            }
        }
    )
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
