package com.saeongjima.designsystem.component.dropdownmenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.component.textfield.DropdownTextField
import com.saeongjima.designsystem.extension.modifier.crop
import com.saeongjima.designsystem.theme.DanjamTheme

private const val MaxVisibleItemCount = 5

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DanjamExposedDropDownMenu(options: List<String>, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        modifier = modifier.wrapContentWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        DropdownTextField(
            value = selectedOptionText,
            onValueChange = {},
            isExpanded = expanded,
            modifier = Modifier.menuAnchor()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            offset = DpOffset(x = 0.dp, y = 8.dp),
            modifier = Modifier
                .crop(vertical = 8.dp)
                .heightIn(max = (MaxVisibleItemCount * 48).dp)
                .exposedDropdownSize()

        ) {
            options.forEach { selectionOption ->
                DanjamDropdownMenuItem(
                    value = selectionOption,
                    isSelected = selectionOption == selectedOptionText,
                    modifier = Modifier.clickable {
                        selectedOptionText = selectionOption
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun DanjamExposedDropDownMenuPreview() {
    DanjamTheme {
        DanjamExposedDropDownMenu(List(5) { "$it" })
    }
}
