package com.saeongjima.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black300
import com.saeongjima.designsystem.theme.Black400
import com.saeongjima.designsystem.theme.Black600
import com.saeongjima.designsystem.theme.Black800
import com.saeongjima.designsystem.theme.Black900
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.White

@Composable
fun UniversityCertificationScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "학교 인증",
            style = MaterialTheme.typography.displayLarge,
            color = Black950,
        )
        Row(
            modifier = Modifier
                .padding(top = 44.dp)
                .background(Black300, shape = RoundedCornerShape(4.dp))
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Icon(
                painter = painterResource(id = com.saeongjima.designsystem.R.drawable.ic_camera_24),
                contentDescription = null,
                tint = Black400,
                modifier = Modifier
                    .size(56.dp)
                    .background(Black200, shape = RoundedCornerShape(4.dp))
                    .padding(8.dp),
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "재학 중인 대학교 개인정보 페이지 혹은\n학생증(합격증) 사진을 올려주세요",
                    style = MaterialTheme.typography.titleLarge.copy(lineHeight = 20.sp),
                    color = Black800,
                )
                Text(
                    text = "10MB 내로 업로드 바랍니다.",
                    style = MaterialTheme.typography.labelLarge,
                    color = Black600,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 24.dp)
        ) {
            Image(
                painter = painterResource(id = com.saeongjima.designsystem.R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(4.dp)),
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(8.dp)
                    .then(Modifier.size(48.dp))
                    .align(Alignment.TopEnd),
            ) {
                Icon(
                    painter = painterResource(id = com.saeongjima.designsystem.R.drawable.ic_close_up_24),
                    contentDescription = "close up",
                    tint = Black100.copy(alpha = 0.6f),
                    modifier = Modifier
                        .then(Modifier.size(32.dp))
                        .background(Black900.copy(alpha = 0.5f), shape = RoundedCornerShape(4.dp))
                        .align(Alignment.Center),
                )
            }
        }
        MainButton(
            text = "다른사진 선택하기",
            containerColor = MainColor,
            textColor = White,
        ) {
        }
    }
}

@Preview
@Composable
fun UniversityCertificationScreenPreview() {
    DanjamTheme {
        UniversityCertificationScreen(
            modifier = Modifier.padding(
                top = 40.dp,
                start = 24.dp,
                end = 24.dp,
            ),
        )
    }
}
