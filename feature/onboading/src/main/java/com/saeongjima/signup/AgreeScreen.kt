package com.saeongjima.signup

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.saeongjima.designsystem.component.ScrollableView
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.component.checkbox.CheckboxWithLabel
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black700
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.login.R

@Composable
fun AgreeRoute(
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AgreeScreen(
        modifier = modifier,
        onNextButtonClick = onNextButtonClick,
    )
}

@Composable
internal fun AgreeScreen(modifier: Modifier = Modifier, onNextButtonClick: () -> Unit) {
    var openDialog by rememberSaveable { mutableStateOf(false) }
    var dialogText by rememberSaveable { mutableStateOf(PolicyText.TermsOfUse) }

    var isCheckedTermsOfUse by rememberSaveable { mutableStateOf(false) }
    var isCheckedPrivacyPolicy by rememberSaveable { mutableStateOf(false) }

    if (openDialog) {
        FullTextDialog(
            text = stringResource(id = dialogText.res),
            onDismissRequest = { openDialog = false }
        )
    }
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.request_agree_for_service),
            style = MaterialTheme.typography.displayLarge,
            color = Black950,
            modifier = Modifier.padding(top = 64.dp),
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
        ) {
            CheckboxWithLabel(
                isChecked = isCheckedTermsOfUse,
                label = stringResource(R.string.terms_of_use_title),
                onValueChange = { isCheckedTermsOfUse = !isCheckedTermsOfUse },
                modifier = Modifier.offset(x = (-8).dp),
            )
            Text(
                text = stringResource(R.string.see_full_text),
                textDecoration = TextDecoration.Underline,
                color = Black500,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .clickable {
                        openDialog = true
                        dialogText = PolicyText.TermsOfUse
                    },
            )
        }
        ScrollableView(
            modifier = Modifier
                .padding(top = 12.dp)
                .weight(1f)
                .background(Black200, shape = RoundedCornerShape(4.dp)),
        ) {
            Text(
                text = stringResource(R.string.terms_of_use),
                style = MaterialTheme.typography.labelLarge,
                color = Black700,
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
        ) {
            CheckboxWithLabel(
                isChecked = isCheckedPrivacyPolicy,
                label = stringResource(R.string.privacy_policy_title),
                onValueChange = { isCheckedPrivacyPolicy = !isCheckedPrivacyPolicy },
                modifier = Modifier.offset(x = (-8).dp)
            )
            Text(
                text = stringResource(id = R.string.see_full_text),
                textDecoration = TextDecoration.Underline,
                color = Black500,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .clickable {
                        openDialog = true
                        dialogText = PolicyText.PrivacyPolicy
                    },
            )
        }

        ScrollableView(
            modifier = Modifier
                .padding(top = 12.dp)
                .weight(1f)
                .background(Black200, shape = RoundedCornerShape(4.dp)),
        ) {
            Text(
                text = stringResource(R.string.privacy_policy),
                style = MaterialTheme.typography.labelLarge,
                color = Black700,
            )
        }

        MainButton(
            text = stringResource(id = com.saeongjima.designsystem.R.string.main_button_text_next),
            modifier = Modifier.padding(
                top = 12.dp,
                bottom = 28.dp,
            ),
            enabled = isCheckedPrivacyPolicy && isCheckedTermsOfUse,
            containerColor = PointColor1,
            textColor = Black100,
            onClick = onNextButtonClick
        )
    }
}

@Composable
private fun FullTextDialog(
    text: String,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0.8f)
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            IconButton(
                onClick = { onDismissRequest() },
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.End)
            ) {
                Icon(
                    painter = painterResource(id = com.saeongjima.designsystem.R.drawable.ic_exit_24),
                    contentDescription = stringResource(R.string.exit_see_full_text),
                    modifier = modifier.size(36.dp),
                    tint = Black700,
                )
            }
            ScrollableView(
                modifier = modifier
                    .padding(top = 12.dp)
                    .weight(1f)
                    .background(Black200, shape = RoundedCornerShape(4.dp)),
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge,
                    color = Black700,
                )
            }
        }
    }
}

enum class PolicyText(@StringRes val res: Int) {
    TermsOfUse(R.string.terms_of_use),
    PrivacyPolicy(R.string.privacy_policy),
}

@Preview
@Composable
private fun AgreeScreenPreview() {
    DanjamTheme {
        AgreeScreen(onNextButtonClick = {})
    }
}
