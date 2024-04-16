package com.saeongjima.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.component.button.DanjamButton
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.White
import com.saeongjima.login.R

private const val ButtonMarginSize = 16

@Composable
fun SplashScreen(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(dimensionResource(id = com.saeongjima.designsystem.R.dimen.layout_default_padding)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = com.saeongjima.designsystem.R.drawable.logo),
            contentDescription = stringResource(R.string.splash_danjam_logo_description),
            modifier = Modifier
                .width(width = 192.dp)
                .weight(1f),
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DanjamButton(
                text = stringResource(R.string.splash_sign_in_button_text),
                onClick = onSignInClick,
            )
            Spacer(modifier = Modifier.height(ButtonMarginSize.dp))
            DanjamButton(
                text = stringResource(R.string.splash_sign_up_button_text),
                containerColor = White,
                textColor = MainColor,
                onClick = onSignUpClick,
            )
            Spacer(modifier = Modifier.height(ButtonMarginSize.dp))
            Text(
                text = stringResource(R.string.splash_guest_button_text),
                style = MaterialTheme.typography.bodyMedium,
                color = Black950,
                textDecoration = TextDecoration.Underline,
            )
        }
    }
}
