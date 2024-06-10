package com.saeongjima.designsystem.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.R.string.danjam_duplication_check_button_text_field_check_done
import com.saeongjima.designsystem.R.string.danjam_duplication_check_button_text_field_duplication_check
import com.saeongjima.designsystem.component.button.TrailingButton
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.model.DuplicateState
import com.saeongjima.model.DuplicateState.Duplicated
import com.saeongjima.model.DuplicateState.NotChecked
import com.saeongjima.model.DuplicateState.NotDuplicated

@Composable
fun DanjamDuplicationCheckButtonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    hintText: String = "",
    errorText: String = "",
    duplicateState: DuplicateState = NotChecked,
    onButtonClick: () -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
) {
    val focusManager = LocalFocusManager.current

    DanjamBasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        isError = isError,
        hintText = hintText,
        errorText = errorText,
        keyboardType = keyboardType,
        imeAction = imeAction,
        trailingContent = {
            TrailingButton(
                text = when (duplicateState) {
                    NotChecked -> stringResource(
                        danjam_duplication_check_button_text_field_duplication_check
                    )

                    Duplicated -> stringResource(
                        danjam_duplication_check_button_text_field_duplication_check
                    )

                    NotDuplicated -> stringResource(
                        danjam_duplication_check_button_text_field_check_done
                    )
                },
                onClick = {
                    focusManager.clearFocus()
                    onButtonClick()
                },
                enabled = duplicateState == NotChecked && value.isNotEmpty(),
                modifier = Modifier.padding(end = 10.dp)
            )
        }
    )
}

@Preview
@Composable
private fun DanjamTrailingButtonTextFieldPreview() {
    DanjamTheme {
        var text by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }

        Column {
            DanjamDuplicationCheckButtonTextField(
                value = text,
                onValueChange = { text = it },
                duplicateState = NotChecked,
                hintText = "EX) Danjam2",
                isError = isError,
                errorText = "중복된 아이디입니다.",
                onButtonClick = { isError = true },
            )
        }
    }
}
