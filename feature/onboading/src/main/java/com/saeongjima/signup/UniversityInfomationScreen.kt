package com.saeongjima.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.component.DanjamButton
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.White
import com.saeongjima.signin.InputField

@Composable
fun UniversityInformationScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            trailing = {
                DanjamButton(
                    text = "검색하기",
                    textStyle = MaterialTheme.typography.bodyMedium,
                    textColor = White,
                    containerColor = Black500,
                    modifier = Modifier
                        .size(
                            width = 68.dp,
                            height = 28.dp,
                        )
                        .padding(end = 8.dp),
                ) { /* onClick */ }
            },
        )
    }
}

@Preview
@Composable
fun a() {
    DanjamTheme {
        UniversityInformationScreen()
    }
}
