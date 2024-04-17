package com.saeongjima.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.component.DanjamTextField
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.White
import com.saeongjima.signup.departmentselect.DepartmentSelectScreen

@Composable
fun UniversityInformationScreen(modifier: Modifier = Modifier) {
    var openDialog by remember { mutableStateOf(false) }

    if (openDialog) {
        DepartmentSelectScreen {
            openDialog = false
        }
    }

    Column(modifier = modifier) {
        Text(
            text = "학교 정보",
            style = MaterialTheme.typography.displayLarge,
            color = White,
        )
        Spacer(modifier = Modifier.height(44.dp))
        InputBox(title = "입학년도", modifier = Modifier.fillMaxWidth()) {
            DanjamTextField(
                value = "",
                onValueChange = {},
                hintText = "EX) 2024"
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        InputBox(
            title = "학교",
            modifier = Modifier.fillMaxWidth(),
        ) {
            DanjamTextField(value = "", onValueChange = {}, hintText = "EX) 단잠대학교")
        }
        InputBox(
            title = "학과",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        ) {
            DanjamTextField(value = "", onValueChange = {}, hintText = "EX) 단잠과")
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
