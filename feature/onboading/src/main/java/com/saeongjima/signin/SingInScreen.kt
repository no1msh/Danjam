package com.saeongjima.signin

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saeongjima.designsystem.R.dimen
import com.saeongjima.designsystem.R.drawable
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.component.textfield.DanjamBasicTextField
import com.saeongjima.designsystem.component.textfield.SecureTextField
import com.saeongjima.designsystem.theme.Black700
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.Error
import com.saeongjima.designsystem.util.addFocusCleaner
import com.saeongjima.login.R

private const val MinimumIdLength = 1
private const val MinimumPasswordLength = 1

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    signInViewModel: SignInViewModel = hiltViewModel(),
    onCloseClick: () -> Unit,
) {
    val uiState: SignInUiState by signInViewModel.signInUiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = dimensionResource(id = dimen.layout_default_padding),
                end = dimensionResource(id = dimen.layout_default_padding),
                bottom = dimensionResource(id = dimen.layout_default_padding),
            )
            .addFocusCleaner(
                focusManager = LocalFocusManager.current,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Header(
            trailingIcon = drawable.ic_exit_24,
            onIconClick = onCloseClick,
        )
        Text(
            text = stringResource(R.string.sign_in_welcome),
            style = MaterialTheme.typography.displayLarge,
            color = Black950,
            modifier = Modifier.padding(top = 62.dp)
        )
        InputBox(
            title = stringResource(R.string.sign_in_id_title),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 62.dp),
        ) {
            DanjamBasicTextField(
                value = signInViewModel.id.value,
                onValueChange = { signInViewModel.updateId(it) },
                hintText = stringResource(R.string.sign_in_id_hint),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        InputBox(
            title = stringResource(R.string.sign_in_password_title),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
        ) {
            SecureTextField(
                value = signInViewModel.password.value,
                onValueChange = { signInViewModel.updatePassword(it) },
                hintText = stringResource(R.string.sign_in_password_hint),
                onInputFinish = { signInViewModel.signIn() },
                modifier = Modifier.padding(top = 8.dp),
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 12.dp)
                .alpha(if (uiState is SignInUiState.Fail) 1f else 0f),
        ) {
            Icon(
                painter = painterResource(id = drawable.ic_error_24),
                contentDescription = null,
                tint = Error,
                modifier = Modifier.size(22.dp),
            )
            Text(
                text = stringResource(R.string.sign_in_wrong_id_or_password),
                style = MaterialTheme.typography.bodyLarge,
                color = Error,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        MainButton(
            text = stringResource(R.string.sign_in_sign_in_button_text),
            enabled = signInViewModel.id.value.length >= MinimumIdLength &&
                    signInViewModel.password.value.length >= MinimumPasswordLength,
            modifier = Modifier.padding(top = 48.dp),
            onClick = { signInViewModel.signIn() }
        )
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    @DrawableRes trailingIcon: Int,
    onIconClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = drawable.logo),
            contentDescription = stringResource(id = R.string.danjam_logo_description),
            modifier = Modifier.size(width = 80.dp, height = 28.dp),
        )
        IconButton(onClick = onIconClick) {
            Icon(
                painter = painterResource(id = trailingIcon),
                contentDescription = stringResource(R.string.ic_exit_description),
                tint = Black700,
                modifier = Modifier.size(32.dp),
            )
        }
    }
}

@Composable
private fun InputBox(
    title: String,
    modifier: Modifier = Modifier,
    textField: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = Black950,
            modifier = Modifier.fillMaxWidth(),
        )
        textField()
    }
}
