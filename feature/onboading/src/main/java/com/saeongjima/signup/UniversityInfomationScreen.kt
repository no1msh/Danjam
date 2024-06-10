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
import com.saeongjima.designsystem.component.textfield.DanjamBasicTextField
import com.saeongjima.designsystem.component.textfield.InputBox
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.designsystem.theme.White
import com.saeongjima.login.R.string.university_information_screen_department_input_box_example
import com.saeongjima.login.R.string.university_information_screen_department_input_box_title
import com.saeongjima.login.R.string.university_information_screen_entrance_year_input_box_example
import com.saeongjima.login.R.string.university_information_screen_entrance_year_input_box_title
import com.saeongjima.login.R.string.university_information_screen_title
import com.saeongjima.login.R.string.university_information_screen_university_input_box_example
import com.saeongjima.login.R.string.university_information_screen_university_input_box_title
import com.saeongjima.model.Department
import com.saeongjima.signup.departmentselect.DepartmentItemUiState
import com.saeongjima.signup.departmentselect.DepartmentSelectScreen
import com.saeongjima.signup.universityinformation.UniversityInformationUiState

@Composable
fun UniversityInformationRoute(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    onNextButtonClick: () -> Unit,
) {
    val uiState by viewModel.universityInformationUiState.collectAsStateWithLifecycle()

    UniversityInformationScreen(
        modifier = modifier,
        uiState = uiState,
        onUserEntranceYearChanged = viewModel::updateUserEntranceYear,
        onUserUniversityChanged = viewModel::updateUserUniversity,
        onUserDepartmentChanged = viewModel::updateUserDepartment,
        onNextButtonClick = onNextButtonClick,
    )
}

@Composable
fun UniversityInformationScreen(
    modifier: Modifier = Modifier,
    uiState: UniversityInformationUiState,
    onUserEntranceYearChanged: (String) -> Unit,
    onUserUniversityChanged: (String) -> Unit,
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
            text = stringResource(university_information_screen_title),
            style = MaterialTheme.typography.displayLarge,
            color = White,
        )
        Spacer(modifier = Modifier.height(44.dp))
        InputBox(
            title = stringResource(university_information_screen_entrance_year_input_box_title),
            modifier = Modifier.fillMaxWidth()
        ) {
            DanjamExposedDropDownMenu(
                options = uiState.entranceYears,
                modifier = Modifier.fillMaxWidth(),
                initialValue = uiState.userEntranceYear,
                hintText = stringResource(
                    university_information_screen_entrance_year_input_box_example
                ),
                onValueChanged = onUserEntranceYearChanged,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        InputBox(
            title = stringResource(university_information_screen_university_input_box_title),
            modifier = Modifier.fillMaxWidth(),
        ) {
            DanjamExposedDropDownMenu(
                options = uiState.universities,
                modifier = Modifier.fillMaxWidth(),
                initialValue = uiState.userUniversity,
                hintText = stringResource(university_information_screen_university_input_box_example),
                onValueChanged = onUserUniversityChanged,
            )
        }
        InputBox(
            title = stringResource(university_information_screen_department_input_box_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        ) {
            DanjamBasicTextField(
                value = uiState.userDepartment,
                onValueChange = {},
                hintText = stringResource(university_information_screen_department_input_box_example),
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
            enabled = uiState.hasMetAllConditions(),
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
            uiState = UniversityInformationUiState(
                entranceYears = listOf("2024", "2023"),
                universities = listOf("홍익대, 고려대"),
                departments = listOf("컴퓨터 공학과", "패션 디자인 학과"),
                userUniversity = "고려대",
                userEntranceYear = "2018",
                userDepartment = "컴퓨터 공학",
            ),
            onUserUniversityChanged = {},
            onUserEntranceYearChanged = {},
            onUserDepartmentChanged = {},
            onNextButtonClick = {}
        )
    }
}
