package com.saeongjima.signup

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.component.DanjamTextField
import com.saeongjima.designsystem.component.textfield.SecureTextField
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.Correct
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.Error

@Composable
fun SignInInformationScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "로그인 정보",
            color = Black950,
            style = MaterialTheme.typography.displayLarge,
        )
        InputBox(
            title = "아이디",
            modifier = Modifier.padding(top = 44.dp),
        ) {
            DanjamTextField(
                value = "",
                onValueChange = {},
                hintText = "EX) honggildong",
                hasTrailingButton = true,
                trailingButtonText = "중복확인",
                onTrailingButtonClick = {
                    false
                }
            )
        }
        InputValidator(
            label = "20자 이내",
            validateState = ValidateState.ValidateSuccess,
            modifier = Modifier.padding(top = 12.dp),
        )
        InputBox(
            title = "비밀번호",
            modifier = Modifier.padding(top = 32.dp),
        ) {
            SecureTextField(
                value = "",
                onValueChange = {},
                hintText = "EX) danjam2468",
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 12.dp),
        ) {
            InputValidator(
                label = "영문 포함",
                validateState = ValidateState.ValidateSuccess,
            )
            InputValidator(
                label = "숫자 포함",
                validateState = ValidateState.ValidateFailure,
                modifier = Modifier.padding(start = 16.dp),
            )
            InputValidator(
                label = "8 ~ 20자 이내",
                validateState = ValidateState.ValidateSuccess,
                modifier = Modifier.padding(start = 16.dp),
            )
        }

        InputBox(
            title = "비밀번호 확인",
            modifier = Modifier.padding(top = 44.dp),
        ) {
            SecureTextField(value = "", onValueChange = {}, hintText = "비밀번호를 한 번 더 입력해주세요")
        }
        InputValidator(
            label = "일치",
            validateState = ValidateState.ValidateYet,
            modifier = Modifier.padding(top = 12.dp),
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
        SignInInformationScreen(Modifier.fillMaxSize())
    }
}
