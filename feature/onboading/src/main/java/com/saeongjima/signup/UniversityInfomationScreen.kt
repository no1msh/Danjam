package com.saeongjima.signup

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.component.dropdownmenu.DanjamExposedDropDownMenu
import com.saeongjima.designsystem.component.textfield.DanjamTextField
import com.saeongjima.designsystem.component.textfield.InputBox
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.designsystem.theme.White
import com.saeongjima.model.Department
import com.saeongjima.signup.departmentselect.DepartmentItemUiState
import com.saeongjima.signup.departmentselect.DepartmentSelectScreen

@Composable
fun UniversityInformationRoute(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    onNextButtonClick: () -> Unit,
) {
    val entranceYears by viewModel.entranceYear.collectAsStateWithLifecycle()
    val universities by viewModel.universities.collectAsStateWithLifecycle()
    val departments by viewModel.departments.collectAsStateWithLifecycle()

    val userEntranceYear by viewModel.userEntranceYear.collectAsStateWithLifecycle()
    val userUniversity by viewModel.userUniversity.collectAsStateWithLifecycle()
    val userDepartment by viewModel.userDepartment.collectAsStateWithLifecycle()

    UniversityInformationScreen(
        modifier = modifier,
        entranceYears = entranceYears,
        universities = universities,
        departments = departments,
        userEntranceYear = userEntranceYear,
        onUserEntranceYearChanged = viewModel::updateUserEntranceYear,
        userUniversity = userUniversity,
        onUserUniversityChanged = viewModel::updateUserUniversity,
        userDepartment = userDepartment,
        onUserDepartmentChanged = viewModel::updateUserDepartment,
        onNextButtonClick = onNextButtonClick
    )
}

@Composable
fun UniversityInformationScreen(
    modifier: Modifier = Modifier,
    entranceYears: List<String>,
    universities: List<String>,
    departments: List<String>,
    userEntranceYear: String,
    onUserEntranceYearChanged: (String) -> Unit,
    userUniversity: String,
    onUserUniversityChanged: (String) -> Unit,
    userDepartment: String,
    onUserDepartmentChanged: (String) -> Unit,
    onNextButtonClick: () -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

    if (openDialog) {
        DepartmentSelectScreen(
            onValueSelected = { onUserDepartmentChanged(it) },
            onDismissRequest = { openDialog = false },
            departments = listOf(
                DepartmentItemUiState(1, Department("게임소프트웨어전공", "게임학부")),
                DepartmentItemUiState(2, Department("게임그래픽디자인전공", "게임학부")),
            ),
        )
    }

    Column(modifier = modifier) {
        Text(
            text = "학교 정보",
            style = MaterialTheme.typography.displayLarge,
            color = White,
        )
        Spacer(modifier = Modifier.height(44.dp))
        InputBox(title = "입학년도", modifier = Modifier.fillMaxWidth()) {
            DanjamExposedDropDownMenu(
                options = entranceYears,
                modifier = Modifier.fillMaxWidth(),
                initialValue = userEntranceYear,
                hintText = "EX) 2024",
                onValueChanged = onUserEntranceYearChanged,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        InputBox(
            title = "학교",
            modifier = Modifier.fillMaxWidth(),
        ) {
            DanjamExposedDropDownMenu(
                options = universities,
                modifier = Modifier.fillMaxWidth(),
                initialValue = userUniversity,
                hintText = "EX) 단잠대학교",
                onValueChanged = onUserUniversityChanged,
            )
        }
        InputBox(
            title = "학과",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        ) {
            DanjamTextField(
                value = userDepartment,
                onValueChange = {},
                hintText = "EX) 단잠과",
                isReadOnly = true,
                isEnabled = false,
                modifier = Modifier.clickable {
                    openDialog = true
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        MainButton(
            text = stringResource(id = R.string.main_button_text_next),
            modifier = Modifier.padding(bottom = 28.dp),
            enabled = userEntranceYear.isNotBlank() && userUniversity.isNotBlank() && userDepartment.isNotBlank(),
            containerColor = PointColor1,
            textColor = Black100,
            onClick = { onNextButtonClick() }
        )
    }
}

@Preview
@Composable
private fun UniversityInformationScreenPreview() {
    DanjamTheme {
        UniversityInformationScreen(
            entranceYears = listOf("2024", "2023"),
            universities = listOf("홍익대, 고려대"),
            departments = listOf("컴퓨터 공학과", "패션 디자인 학과"),
            userUniversity = "고려대",
            onUserUniversityChanged = {},
            userEntranceYear = "2018",
            onUserEntranceYearChanged = {},
            userDepartment = "컴퓨터 공학",
            onUserDepartmentChanged = {},
            onNextButtonClick = {}
        )
    }
}
