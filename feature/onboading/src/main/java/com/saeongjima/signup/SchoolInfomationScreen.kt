package com.saeongjima.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.component.DanjamButton
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.White
import com.saeongjima.signin.InputField

@Composable
fun SchoolInformationScreen(modifier: Modifier = Modifier) {
    var currentPage by rememberSaveable { mutableFloatStateOf(0f) }
    Column(
        modifier
            .fillMaxSize()
            .background(Black100),
    ) {
        OnboardingTopAppBar(
            leadingIcon = R.drawable.ic_back_24,
            trailingIcon = R.drawable.ic_exit_24,
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
                text = "학교 정보",
                style = MaterialTheme.typography.displayLarge,
                color = White,
            )
            Spacer(modifier = Modifier.height(44.dp))
            InputField(title = "입학년도", hint = "EX) 2024", modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(32.dp))
            InputField(
                title = "학교",
                hint = "EX) 단잠대학교",
                modifier = Modifier.fillMaxWidth(),
            )
            InputField(
                title = "학과",
                hint = "EX) 단잠과",
                modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                trailing = {
                    DanjamButton(
                        text = "검색하기",
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

@Preview
@Composable
fun a() {
    DanjamTheme {
        SchoolInformationScreen()
    }
}
