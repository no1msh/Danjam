package com.saeongjima.signup.signininformation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.R.string.main_button_text_next
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.component.textfield.DanjamTextField
import com.saeongjima.designsystem.component.textfield.InputBox
import com.saeongjima.designsystem.component.textfield.SecureTextField
import com.saeongjima.designsystem.extension.modifier.verticalScrollBar
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.Correct
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.Error
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.login.R.string.sign_in_information_screen_duplication_check
import com.saeongjima.login.R.string.sign_in_information_screen_duplication_check_done
import com.saeongjima.login.R.string.sign_in_information_screen_id_input_box_description
import com.saeongjima.login.R.string.sign_in_information_screen_id_input_box_example
import com.saeongjima.login.R.string.sign_in_information_screen_id_input_box_title
import com.saeongjima.login.R.string.sign_in_information_screen_id_input_validator_length_limit
import com.saeongjima.login.R.string.sign_in_information_screen_nickname_input_box_description
import com.saeongjima.login.R.string.sign_in_information_screen_nickname_input_box_title
import com.saeongjima.login.R.string.sign_in_information_screen_nickname_input_validator_length_limit
import com.saeongjima.login.R.string.sign_in_information_screen_password_input_box_example
import com.saeongjima.login.R.string.sign_in_information_screen_password_input_box_title
import com.saeongjima.login.R.string.sign_in_information_screen_password_input_validator_contain_alphabet
import com.saeongjima.login.R.string.sign_in_information_screen_password_input_validator_contain_number
import com.saeongjima.login.R.string.sign_in_information_screen_password_input_validator_length_limit
import com.saeongjima.login.R.string.sign_in_information_screen_password_repeat_input_box_description
import com.saeongjima.login.R.string.sign_in_information_screen_password_repeat_input_box_title
import com.saeongjima.login.R.string.sign_in_information_screen_password_repeat_input_validator_same
import com.saeongjima.login.R.string.sign_in_information_screen_title
import com.saeongjima.signup.DuplicateState

@Composable
fun SignInInformationRoute(
    modifier: Modifier = Modifier,
    onNextButtonClick: (SignInInformationUiState) -> Unit,
    viewModel: SignInInformationViewModel = hiltViewModel()
) {
    val uiState by viewModel.signInInformationUiState.collectAsStateWithLifecycle()

    SignInInformationScreen(
        modifier = modifier,
        uiState = uiState,
        onIdChanged = viewModel::updateId,
        onNicknameChanged = viewModel::updateNickname,
        onPasswordChanged = viewModel::updatePassword,
        onPasswordRepeatChanged = viewModel::updatePasswordRepeat,
        onIdDuplicationCheck = viewModel::checkIdDuplication,
        onNicknameDuplicationCheck = viewModel::checkNicknameDuplication,
        onNextButtonClick = onNextButtonClick,
    )
}

@Composable
fun SignInInformationScreen(
    modifier: Modifier = Modifier,
    uiState: SignInInformationUiState,
    onIdChanged: (String) -> Unit,
    onNicknameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatChanged: (String) -> Unit,
    onIdDuplicationCheck: () -> Unit,
    onNicknameDuplicationCheck: () -> Unit,
    onNextButtonClick: (SignInInformationUiState) -> Unit,
) {
    val scrollState: ScrollState = rememberScrollState()

    Column(modifier = modifier) {

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .verticalScrollBar(scrollState, isAlwaysVisible = true)
                .weight(1f)
        ) {
            Text(
                text = stringResource(sign_in_information_screen_title),
                color = Black950,
                style = MaterialTheme.typography.displayLarge,
            )
            InputBox(
                title = stringResource(sign_in_information_screen_id_input_box_title),
                description = stringResource(sign_in_information_screen_id_input_box_description),
                modifier = Modifier.padding(top = 44.dp),
            ) {
                DanjamTextField(
                    value = uiState.id.value,
                    onValueChange = onIdChanged,
                    hintText = stringResource(sign_in_information_screen_id_input_box_example),
                    hasTrailingButton = true,
                    isError = uiState.isIdDuplication == DuplicateState.Duplicated,
                    trailingButtonText = when (uiState.isIdDuplication) {
                        DuplicateState.NotChecked -> stringResource(
                            sign_in_information_screen_duplication_check
                        )

                        DuplicateState.Duplicated -> stringResource(
                            sign_in_information_screen_duplication_check
                        )

                        DuplicateState.NotDuplicated -> stringResource(
                            sign_in_information_screen_duplication_check_done
                        )
                    },
                    onTrailingButtonClick = onIdDuplicationCheck
                )
            }
            InputValidator(
                label = stringResource(sign_in_information_screen_id_input_validator_length_limit),
                validateState = when {
                    uiState.id.value.isEmpty() -> ValidateState.ValidateYet
                    uiState.id.isKeepRange() -> ValidateState.ValidateSuccess
                    else -> ValidateState.ValidateFailure
                },
                modifier = Modifier.padding(top = 12.dp),
            )

            InputBox(
                title = stringResource(sign_in_information_screen_nickname_input_box_title),
                description = stringResource(
                    sign_in_information_screen_nickname_input_box_description
                ),
                modifier = Modifier.padding(top = 32.dp),
            ) {
                DanjamTextField(
                    value = uiState.nickname.value,
                    onValueChange = onNicknameChanged,
                    hintText = stringResource(com.saeongjima.login.R.string.sign_in_information_screen_nickname_input_box_example),
                    hasTrailingButton = true,
                    isError = uiState.isNicknameDuplication == DuplicateState.Duplicated,
                    trailingButtonText = when (uiState.isNicknameDuplication) {
                        DuplicateState.NotChecked -> stringResource(
                            sign_in_information_screen_duplication_check
                        )

                        DuplicateState.Duplicated -> stringResource(
                            sign_in_information_screen_duplication_check
                        )

                        DuplicateState.NotDuplicated -> stringResource(
                            sign_in_information_screen_duplication_check_done
                        )
                    },
                    onTrailingButtonClick = onNicknameDuplicationCheck
                )
            }
            InputValidator(
                label = stringResource(
                    sign_in_information_screen_nickname_input_validator_length_limit
                ),
                validateState = when {
                    uiState.nickname.value.isEmpty() -> ValidateState.ValidateYet
                    uiState.nickname.isKeepRange() -> ValidateState.ValidateSuccess
                    else -> ValidateState.ValidateFailure
                },
                modifier = Modifier.padding(top = 12.dp),
            )

            InputBox(
                title = stringResource(sign_in_information_screen_password_input_box_title),
                modifier = Modifier.padding(top = 32.dp),
            ) {
                SecureTextField(
                    value = uiState.password.value,
                    onValueChange = onPasswordChanged,
                    hintText = stringResource(sign_in_information_screen_password_input_box_example),
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 12.dp),
            ) {
                InputValidator(
                    label = stringResource(
                        sign_in_information_screen_password_input_validator_contain_alphabet
                    ),
                    validateState = when {
                        uiState.password.value.isEmpty() -> ValidateState.ValidateYet
                        uiState.password.hasAlphabet() -> ValidateState.ValidateSuccess
                        else -> ValidateState.ValidateFailure
                    },
                )
                InputValidator(
                    label = stringResource(
                        sign_in_information_screen_password_input_validator_contain_number
                    ),
                    validateState = when {
                        uiState.password.value.isEmpty() -> ValidateState.ValidateYet
                        uiState.password.hasNumber() -> ValidateState.ValidateSuccess
                        else -> ValidateState.ValidateFailure
                    },
                    modifier = Modifier.padding(start = 16.dp),
                )
                InputValidator(
                    label = stringResource(id = sign_in_information_screen_password_input_validator_length_limit),
                    validateState = when {
                        uiState.password.value.isEmpty() -> ValidateState.ValidateYet
                        uiState.password.isKeepRange() -> ValidateState.ValidateSuccess
                        else -> ValidateState.ValidateFailure
                    },
                    modifier = Modifier.padding(start = 16.dp),
                )
            }

            InputBox(
                title = stringResource(sign_in_information_screen_password_repeat_input_box_title),
                modifier = Modifier.padding(top = 32.dp),
            ) {
                SecureTextField(
                    value = uiState.passwordRepeat.value,
                    onValueChange = onPasswordRepeatChanged,
                    hintText = stringResource(
                        sign_in_information_screen_password_repeat_input_box_description
                    )
                )
            }
            InputValidator(
                label = stringResource(
                    sign_in_information_screen_password_repeat_input_validator_same
                ),
                validateState = when {
                    uiState.passwordRepeat.value.isEmpty() -> ValidateState.ValidateYet
                    uiState.passwordRepeat == uiState.password -> ValidateState.ValidateSuccess
                    else -> ValidateState.ValidateFailure
                },
                modifier = Modifier.padding(top = 12.dp),
            )
        }
        MainButton(
            text = stringResource(id = main_button_text_next),
            modifier = Modifier.padding(top = 24.dp, bottom = 28.dp),
            enabled = uiState.hasMetAllConditions(),
            containerColor = PointColor1,
            textColor = Black100,
            onClick = { onNextButtonClick(uiState) }
        )
    }
}

@Composable
private fun InputValidator(
    label: String,
    validateState: ValidateState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = validateState.color,
            modifier = Modifier.padding(vertical = 2.dp)
        )
        Icon(
            painter = painterResource(id = validateState.iconRes),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 2.dp)
                .size(validateState.iconSize),
            tint = validateState.color,
        )
    }
}


enum class ValidateState(
    val color: Color,
    @DrawableRes val iconRes: Int,
    val iconSize: Dp,
) {
    ValidateYet(Black500, R.drawable.ic_check_24, 16.dp),
    ValidateSuccess(Correct, R.drawable.ic_check_24, 16.dp),
    ValidateFailure(Error, R.drawable.ic_exit_24, 12.dp),
}

@Preview
@Composable
fun SignInInformationScreenPreview() {
    DanjamTheme {
        SignInInformationScreen(
            Modifier.fillMaxSize(),
            uiState = SignInInformationUiState(),
            onIdChanged = {},
            onNicknameChanged = {},
            onPasswordChanged = {},
            onPasswordRepeatChanged = {},
            onIdDuplicationCheck = {},
            onNicknameDuplicationCheck = {},
            onNextButtonClick = {}
        )
    }
}
