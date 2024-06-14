package com.saeongjima.signup.departmentselect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.component.textfield.DanjamBasicTextField
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black700
import com.saeongjima.designsystem.theme.Black800
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.login.R.string.department_select_screen_department_search_description
import com.saeongjima.login.R.string.department_select_screen_department_search_title
import com.saeongjima.login.R.string.department_select_screen_not_found_description
import com.saeongjima.login.R.string.department_select_screen_select_done_button_text
import com.saeongjima.login.R.string.department_select_screen_title
import com.saeongjima.model.Department

@Composable
fun DepartmentSelectScreen(
    modifier: Modifier = Modifier,
    departments: List<Department>,
    onDismissRequest: () -> Unit,
    onValueSelected: (String) -> Unit,
) {
    var input: String by remember { mutableStateOf("") }

    var selectedItem: Department? by remember { mutableStateOf(null) }

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Black100)
                .padding(horizontal = 24.dp)
        ) {
            IconButton(
                onClick = { onDismissRequest() },
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.End)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_exit_24),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = Black700,
                )
            }
            Text(
                text = stringResource(department_select_screen_title),
                style = MaterialTheme.typography.displayLarge,
                color = Black950,
            )

            Text(
                text = stringResource(department_select_screen_department_search_title),
                style = MaterialTheme.typography.headlineLarge,
                color = Black950,
                modifier = Modifier.padding(top = 44.dp)
            )
            DanjamBasicTextField(
                value = input,
                onValueChange = { input = it },
                hintText = stringResource(department_select_screen_department_search_description),
                modifier = Modifier.padding(top = 12.dp),
                leadingContent = {
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp),
                        tint = Black500,
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
            )

            val items = departments.filter { it.name.contains(input) }

            if (items.isEmpty()) {
                NotFoundScreen(modifier = Modifier.weight(1f))
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f),
                    contentPadding = PaddingValues(top = 24.dp)
                ) {
                    items(
                        items = items,
                        key = { item -> item.id }
                    ) {
                        DepartmentItem(
                            item = it,
                            isSelected = selectedItem == it,
                        ) {
                            selectedItem = it
                        }
                    }
                }
            }
            MainButton(
                text = stringResource(department_select_screen_select_done_button_text),
                enabled = selectedItem != null,
                modifier = Modifier.padding(vertical = 28.dp)
            ) {
                selectedItem?.let {
                    onValueSelected(it.name)
                }
                onDismissRequest()
            }
        }
    }
}

@Composable
private fun NotFoundScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(department_select_screen_not_found_description),
            color = Black800,
            style = MaterialTheme.typography.titleLarge.copy(lineHeight = 24.sp),
            textAlign = TextAlign.Center,
        )
        Image(
            painter = painterResource(id = R.drawable.img_danijamidridada),
            contentDescription = null,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun DepartmentItem(
    item: Department,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(68.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.titleLarge,
            color = if (isSelected) PointColor1 else Black800
        )
        Text(
            text = item.division,
            style = MaterialTheme.typography.labelLarge,
            color = Black800,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview
@Composable
fun DepartmentSelectScreenPreview() {
    DanjamTheme {
        DepartmentSelectScreen(
            modifier = Modifier.padding(top = 24.dp),
            onDismissRequest = {},
            departments = listOf(
                Department(1, "게임소프트웨어전공", "게임학부"),
                Department(2, "게임그래픽디자인전공", "게임학부"),
            ),
            onValueSelected = {},
        )
    }
}
