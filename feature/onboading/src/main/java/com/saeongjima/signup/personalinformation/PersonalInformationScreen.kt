package com.saeongjima.signup.personalinformation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saeongjima.designsystem.R.string.main_button_text_next
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.component.dialog.LoadingDialog
import com.saeongjima.designsystem.component.textfield.DanjamBasicTextField
import com.saeongjima.designsystem.component.textfield.DanjamDuplicationCheckButtonTextField
import com.saeongjima.designsystem.component.textfield.InputBox
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black300
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.designsystem.theme.White
import com.saeongjima.login.R
import com.saeongjima.login.R.string.personal_information_birthday_input_box_hint
import com.saeongjima.login.R.string.personal_information_email_input_box_error_text
import com.saeongjima.login.R.string.personal_information_email_input_box_hint
import com.saeongjima.login.R.string.personal_information_email_input_box_title
import com.saeongjima.login.R.string.personal_information_gender_selector_female
import com.saeongjima.login.R.string.personal_information_gender_selector_male
import com.saeongjima.model.DuplicateState

@Composable
fun PersonalInformationRoute(
    onNextButtonClick: (PersonalInformationUiState) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PersonalInformationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PersonalInformationScreen(
        uiState = uiState,
        onNameChanged = viewModel::updateName,
        onGenderChanged = viewModel::updateGender,
        onBirthDayChanged = viewModel::updateBirthDay,
        onEmailChanged = viewModel::updateEmail,
        onEmailValidateButtonClick = viewModel::checkValidationEmail,
        modifier = modifier,
        onNextButtonClick = onNextButtonClick,
    )
}

@Composable
internal fun PersonalInformationScreen(
    modifier: Modifier = Modifier,
    uiState: PersonalInformationUiState,
    onNameChanged: (String) -> Unit,
    onGenderChanged: (Boolean) -> Unit,
    onBirthDayChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onEmailValidateButtonClick: () -> Unit,
    onNextButtonClick: (PersonalInformationUiState) -> Unit,
) {
    if (uiState.isLoading) {
        LoadingDialog(onDismissRequest = { /*TODO*/ })
    }

    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.personal_information_title),
            style = MaterialTheme.typography.displayLarge,
            color = White,
        )
        Spacer(modifier = Modifier.height(44.dp))
        InputBox(
            title = stringResource(R.string.personal_information_name_input_box_title),
            modifier = Modifier.fillMaxWidth()
        ) {
            DanjamBasicTextField(
                value = uiState.name,
                onValueChange = onNameChanged,
                hintText = stringResource(R.string.personal_information_name_input_box_hint),
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        }
        Text(
            text = stringResource(R.string.personal_information_gender_select_title),
            style = MaterialTheme.typography.headlineLarge,
            color = Black950,
            modifier = Modifier.padding(top = 32.dp),
        )
        GenderSelector(
            isMale = uiState.isMale,
            onGenderChanged = onGenderChanged,
            modifier = Modifier.padding(top = 12.dp),
        )
        Spacer(modifier = Modifier.height(32.dp))
        InputBox(
            title = stringResource(R.string.personal_information_birthday_input_box_title),
            modifier = Modifier.fillMaxWidth(),
        ) {
            DanjamBasicTextField(
                value = uiState.birthDay,
                onValueChange = onBirthDayChanged,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                hintText = stringResource(personal_information_birthday_input_box_hint)
            )
        }
        InputBox(
            title = stringResource(personal_information_email_input_box_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        ) {
            DanjamDuplicationCheckButtonTextField(
                value = uiState.email.value,
                onValueChange = onEmailChanged,
                hintText = stringResource(personal_information_email_input_box_hint),
                isError = uiState.isValidEmail == DuplicateState.Duplicated,
                errorText = stringResource(personal_information_email_input_box_error_text),
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
                duplicateState = uiState.isValidEmail,
                onButtonClick = onEmailValidateButtonClick,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        MainButton(
            text = stringResource(id = main_button_text_next),
            modifier = Modifier.padding(bottom = 28.dp),
            enabled = uiState.hasMetAllConditions(),
            containerColor = PointColor1,
            textColor = Black100,
            onClick = { onNextButtonClick(uiState) }
        )
    }
}

@Composable
fun GenderSelector(
    isMale: Boolean,
    onGenderChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        MainButton(
            text = stringResource(personal_information_gender_selector_male),
            containerColor = if (isMale) MainColor else Black300,
            textColor = White,
            textStyle = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
        ) {
            onGenderChanged(true)
        }

        Spacer(modifier = Modifier.width(12.dp))
        MainButton(
            text = stringResource(personal_information_gender_selector_female),
            containerColor = if (!isMale) MainColor else Black300,
            textColor = White,
            textStyle = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
        ) {
            onGenderChanged(false)
        }
    }
}

@Composable
fun OnboardingTopAppBar(
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    leadingIconDescription: String? = null,
    trailingIconDescription: String? = null,
    onLeadingIconClick: () -> Unit = {},
    onTrailingIconClick: () -> Unit = {},
    title: String? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Black100)
            .padding(horizontal = 24.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            leadingIcon?.let {
                IconButton(onClick = onLeadingIconClick, modifier = Modifier.size(40.dp)) {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = leadingIconDescription,
                        tint = Black950,
                        modifier = Modifier.size(32.dp),
                    )
                }
            }
            title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.displayLarge,
                    color = White,
                )
            }
        }
        trailingIcon?.let {
            IconButton(
                onClick = onTrailingIconClick,
                modifier = Modifier.size(40.dp),
            ) {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = trailingIconDescription,
                    tint = Black950,
                    modifier = Modifier.size(32.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun PersonalInformationScreenPreview() {
    DanjamTheme {
        PersonalInformationScreen(
            onNextButtonClick = {},
            uiState = PersonalInformationUiState(),
            onNameChanged = {},
            onGenderChanged = {},
            onBirthDayChanged = {},
            onEmailChanged = {},
            onEmailValidateButtonClick = {},
        )
    }
}
