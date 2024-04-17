package com.saeongjima.signup

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.component.textfield.DanjamTextField
import com.saeongjima.designsystem.component.button.DanjamButton
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black300
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.White

@Composable
fun PersonalInformationScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "개인 정보",
            style = MaterialTheme.typography.displayLarge,
            color = White,
        )
        Spacer(modifier = Modifier.height(44.dp))
        InputBox(title = "이름", modifier = Modifier.fillMaxWidth()) {
            DanjamTextField(value = "", onValueChange = {}, hintText = "EX) 홍길동")
        }
        Text(
            text = "성별",
            style = MaterialTheme.typography.headlineLarge,
            color = Black950,
            modifier = Modifier.padding(top = 32.dp),
        )
        GenderSelector(modifier = Modifier.padding(top = 12.dp))
        Spacer(modifier = Modifier.height(32.dp))
        InputBox(
            title = "생년월일",
            modifier = Modifier.fillMaxWidth(),
        ) {
            DanjamTextField(value = "", onValueChange = {}, hintText = "EX) 010101")
        }
        InputBox(
            title = "이메일",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        ) {
            DanjamTextField(
                value = "",
                onValueChange = {},
                hintText = "EX) danjam@mate.com",
                hasTrailingButton = true,
                onTrailingButtonClick = { false },
            )
        }
    }
}

@Composable
fun GenderSelector(modifier: Modifier = Modifier) {
    var isMale by rememberSaveable { mutableStateOf(true) }

    Row(modifier) {
        DanjamButton(
            text = "남",
            containerColor = if (isMale) MainColor else Black300,
            textColor = White,
            textStyle = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
        ) {
            isMale = true
        }

        Spacer(modifier = Modifier.width(12.dp))
        DanjamButton(
            text = "여",
            containerColor = if (!isMale) MainColor else Black300,
            textColor = White,
            textStyle = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
        ) {
            isMale = false
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

@Preview
@Composable
fun Preview() {
    DanjamTheme {
        PersonalInformationScreen()
    }
}
