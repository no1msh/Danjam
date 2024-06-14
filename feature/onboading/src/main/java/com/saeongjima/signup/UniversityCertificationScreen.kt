package com.saeongjima.signup

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.danjam.context.getTempPngFileUri
import com.danjam.context.processAndAdjustImage
import com.saeongjima.designsystem.component.PhotoSelectGuideBox
import com.saeongjima.designsystem.component.button.CloseUpIconButton
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.component.dialog.PhotoSourceSelectDialog
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.designsystem.theme.White
import com.saeongjima.login.R
import java.io.File

@Composable
fun UniversityCertificationRoute(
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    signUpViewModel: SignUpViewModel = hiltViewModel(),
) {
    val universityCheckImageUri by signUpViewModel.universityCheckImageUri.collectAsStateWithLifecycle()

    UniversityCertificationScreen(
        imageUri = universityCheckImageUri,
        onImageTaken = signUpViewModel::updateUniversityCheckImageUri,
        onNextButtonClick = onNextButtonClick,
        modifier = modifier
    )
}

@Composable
fun UniversityCertificationScreen(
    imageUri: String,
    onImageTaken: (String) -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isOpenPhotoGetterSelectDialog by rememberSaveable { mutableStateOf(false) }
    var isOpenPhotoCloseUpDialog by rememberSaveable { mutableStateOf(false) }

    var selectedImageUri by remember<MutableState<Uri?>> {
        mutableStateOf(null)
    }

    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                selectedImageUri = uri
                onImageTaken(selectedImageUri.toString())
            }
        }
    )

    val takePictureLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isSuccessful ->
            if (isSuccessful) {
                onImageTaken(selectedImageUri.toString())
            }
        }


    if (isOpenPhotoGetterSelectDialog) {
        PhotoSourceSelectDialog(
            onDismissRequest = { isOpenPhotoGetterSelectDialog = false },
            onCameraClicked = {
                selectedImageUri = context.getTempPngFileUri()
                takePictureLauncher.launch(selectedImageUri)
            },
            onGalleryClicked = {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        )
    }

    if (isOpenPhotoCloseUpDialog) {
        FullAsyncImageDialog(
            uri = Uri.parse(imageUri),
            contentDescription = stringResource(R.string.university_certification_screen_image_box_description),
            onDismissRequest = { isOpenPhotoCloseUpDialog = false }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.university_certification_screen_title),
            style = MaterialTheme.typography.displayLarge,
            color = Black950,
        )

        PhotoSelectGuideBox(
            onClick = { isOpenPhotoGetterSelectDialog = true },
            modifier = Modifier.padding(top = 44.dp),
            description = stringResource(R.string.university_certification_screen_description),
        )

        if (imageUri.isNotEmpty()) {
            ImageBox(
                uri = Uri.parse(imageUri),
                onCloseUpClick = { isOpenPhotoCloseUpDialog = true },
                contentDescription = stringResource(R.string.university_certification_screen_image_box_description),
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .weight(1f),
            )
            MainButton(
                text = stringResource(R.string.university_certification_screen_pick_another_photo_button_text),
                containerColor = MainColor,
                textColor = White,
            ) {
                isOpenPhotoGetterSelectDialog = true
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }

        MainButton(
            text = stringResource(com.saeongjima.designsystem.R.string.main_button_text_next),
            modifier = Modifier.padding(top = 16.dp, bottom = 28.dp),
            enabled = imageUri.isNotEmpty(),
            containerColor = PointColor1,
            textColor = Black100,
            onClick = onNextButtonClick,
        )
    }
}


@Composable
private fun ImageBox(
    uri: Uri?,
    contentDescription: String?,
    onCloseUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Black200, RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = uri,
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            modifier = Modifier.clip(RoundedCornerShape(4.dp)),
        )
        CloseUpIconButton(
            onClick = onCloseUpClick,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.TopEnd),
        )
    }
}

@Preview
@Composable
fun UniversityCertificationScreenPreview() {
    DanjamTheme {
        UniversityCertificationScreen(
            "",
            modifier = Modifier.padding(
                top = 40.dp,
                start = 24.dp,
                end = 24.dp,
            ),
            onNextButtonClick = {},
            onImageTaken = {},
        )
    }
}
