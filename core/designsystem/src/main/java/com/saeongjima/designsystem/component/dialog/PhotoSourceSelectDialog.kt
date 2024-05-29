package com.saeongjima.designsystem.component.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.theme.Black100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoSourceSelectDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onCameraClicked: () -> Unit,
    onGalleryClicked: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        containerColor = Black100,
    ) {
        Column(modifier = modifier.padding(bottom = 40.dp)) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth()
                    .height(52.dp)
                    .clickable {
                        onGalleryClicked()
                        onDismissRequest()
                    }
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(R.string.photo_source_select_dialog_get_from_gallery),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start,
                )
            }
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .height(52.dp)
                    .clickable {
                        onCameraClicked()
                        onDismissRequest()
                    }
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(R.string.photo_source_select_dialog_get_from_camera),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}
