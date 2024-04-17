package com.saeongjima.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.theme.Black300
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black900
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.White

@Composable
fun SignUpDoneScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "회원가입 완료",
            style = MaterialTheme.typography.displayLarge,
            color = White,
        )
        Text(
            text = "나를 표현하는 프로필 사진을 등록해주세요!",
            style = MaterialTheme.typography.titleMedium,
            color = Black500,
            modifier = Modifier.padding(top = 12.dp),
        )
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = com.saeongjima.designsystem.R.drawable.ic_camera_24),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .background(Black300, shape = CircleShape)
                    .padding(62.dp)
                    .align(Alignment.CenterHorizontally),
            )

            Column(modifier = Modifier.padding(top = 48.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "아이디",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Black900,
                        modifier = Modifier.width(80.dp),
                    )
                    Text(
                        text = "Honggildong",
                        style = MaterialTheme.typography.titleLarge,
                        color = White,
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "학과",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Black900,
                        modifier = Modifier.width(80.dp),
                    )
                    Text(
                        text = "게임소프트웨어전공",
                        style = MaterialTheme.typography.titleLarge,
                        color = White,
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "학번",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Black900,
                        modifier = Modifier.width(80.dp),
                    )
                    Text(
                        text = "24학번",
                        style = MaterialTheme.typography.titleLarge,
                        color = White,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SignUpDoneScreenPreview() {
    DanjamTheme {
        SignUpDoneScreen()
    }
}
