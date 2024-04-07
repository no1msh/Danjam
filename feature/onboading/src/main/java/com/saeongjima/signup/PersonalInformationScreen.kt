package com.saeongjima.signup

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.component.DanjamButton
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black300
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.White
import com.saeongjima.signin.InputField

@Composable
fun PersonalInformationScreen(modifier: Modifier = Modifier) {
    var currentPage by rememberSaveable { mutableFloatStateOf(0f) }
    DanjamTheme {
        Column(
            modifier
                .fillMaxSize()
                .background(Black100),
        ) {
            OnboardingTopAppBar(
                leadingIcon = com.saeongjima.designsystem.R.drawable.ic_back_24,
                trailingIcon = com.saeongjima.designsystem.R.drawable.ic_exit_24,
                leadingIconDescription = null,
                trailingIconDescription = null,
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        bottom = 24.dp,
                    ),
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                DanJamProgressBar(
                    currentPageIndex = currentPage,
                    totalPageCount = 5,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "개인 정보",
                    style = MaterialTheme.typography.displayLarge,
                    color = White,
                )
                Spacer(modifier = Modifier.height(44.dp))
                InputField(title = "이름", hint = "EX) 홍길동", modifier = Modifier.fillMaxWidth())
                Text(
                    text = "성별",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Black950,
                    modifier = Modifier.padding(top = 32.dp),
                )
                GenderSelector(modifier = Modifier.padding(top = 12.dp))
                Spacer(modifier = Modifier.height(32.dp))
                InputField(
                    title = "생년월일",
                    hint = "EX) 010101",
                    modifier = Modifier.fillMaxWidth(),
                )
                InputField(
                    title = "이메일",
                    hint = "EX) danjam@mate.com",
                    modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                    trailing = {
                        DanjamButton(
                            text = "중복 확인",
                            textStyle = MaterialTheme.typography.bodyMedium,
                            textColor = White,
                            containerColor = Black500,
                            modifier = Modifier.size(
                                width = 68.dp,
                                height = 28.dp,
                            ).padding(end = 8.dp),
                        ) { /* onClick */ }
                    },
                )

                Spacer(modifier = Modifier.weight(1f))
                DanjamButton(text = "다음") {
                }
            }
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
            modifier = Modifier.weight(1f).height(48.dp),
        ) {
            isMale = true
        }

        Spacer(modifier = Modifier.width(12.dp))
        DanjamButton(
            text = "여",
            containerColor = if (!isMale) MainColor else Black300,
            textColor = White,
            textStyle = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1f).height(48.dp),
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
                    style = MaterialTheme.typography.bodyLarge,
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
fun Preview() {
    DanjamTheme {
        PersonalInformationScreen()
    }
}
