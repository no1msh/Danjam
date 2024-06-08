package com.saeongjima.signup.signupdone

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import coil.request.ImageRequest
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.component.dialog.PhotoSourceSelectDialog
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black300
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black900
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.designsystem.theme.White
import com.saeongjima.login.R.string.sign_up_done_screen_department_title
import com.saeongjima.login.R.string.sign_up_done_screen_description
import com.saeongjima.login.R.string.sign_up_done_screen_go_to_sign_in_button_text
import com.saeongjima.login.R.string.sign_up_done_screen_id_title
import com.saeongjima.login.R.string.sign_up_done_screen_profile_image_description
import com.saeongjima.login.R.string.sign_up_done_screen_select_another_photo
import com.saeongjima.login.R.string.sign_up_done_screen_student_number_title
import com.saeongjima.login.R.string.sign_up_done_screen_title
import com.saeongjima.signup.FullAsyncImageDialog
import java.io.File

@Composable
fun SignUpDoneRoute(
    modifier: Modifier,
    viewModel: SignUpDoneViewModel = hiltViewModel(),
) {
    val profileImageUri by viewModel.profileImageUri.collectAsStateWithLifecycle()

    SignUpDoneScreen(
        modifier = modifier,
        imageUri = profileImageUri,
        onImageTaken = viewModel::updateProfileImageUri,
        onNextButtonClick = { /* TODO: 홈 화면 이동 로직*/ }
    )
}

@Composable
fun SignUpDoneScreen(
    modifier: Modifier = Modifier,
    imageUri: String,
    onImageTaken: (String) -> Unit,
    onNextButtonClick: () -> Unit,
) {
    var isOpenPhotoGetterSelectDialog by rememberSaveable { mutableStateOf(false) }
    var isOpenPhotoCloseUpDialog by rememberSaveable { mutableStateOf(false) }

    var selectedImageUri by remember<MutableState<Uri?>> {
        mutableStateOf(null)
    }
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

    val context = LocalContext.current

    if (isOpenPhotoGetterSelectDialog) {
        PhotoSourceSelectDialog(
            onDismissRequest = { isOpenPhotoGetterSelectDialog = false },
            onCameraClicked = {
                val directory = File(context.cacheDir, "images")
                directory.mkdirs()

                val file = File.createTempFile(
                    "selected_image",
                    ".jpg",
                    directory,
                )

                val authority = context.packageName + ".fileprovider"
                selectedImageUri = FileProvider.getUriForFile(context, authority, file)
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
            contentDescription = stringResource(sign_up_done_screen_profile_image_description),
            onDismissRequest = { isOpenPhotoCloseUpDialog = false }
        )
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(sign_up_done_screen_title),
            style = MaterialTheme.typography.displayLarge,
            color = White,
        )
        Text(
            text = stringResource(sign_up_done_screen_description),
            style = MaterialTheme.typography.titleMedium,
            color = Black500,
            modifier = Modifier.padding(top = 12.dp),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageUri.ifEmpty { R.drawable.ic_camera_24 })
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Black300, shape = CircleShape)
                    .clip(CircleShape)
                    .clickable {
                        if (imageUri.isEmpty()) {
                            isOpenPhotoGetterSelectDialog = true
                        } else {
                            isOpenPhotoCloseUpDialog = true
                        }
                    }
                    .size(200.dp)
                    .padding(if (imageUri.isEmpty()) 68.dp else 0.dp)
                    .align(Alignment.CenterHorizontally),
            )

            Column(modifier = Modifier.padding(top = 48.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(sign_up_done_screen_id_title),
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
                        text = stringResource(sign_up_done_screen_department_title),
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
                        text = stringResource(sign_up_done_screen_student_number_title),
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

        if (imageUri.isNotEmpty()) {
            MainButton(
                text = stringResource(sign_up_done_screen_select_another_photo),
                containerColor = MainColor,
                textColor = White,
            ) {
                isOpenPhotoGetterSelectDialog = true
            }
        }

        MainButton(
            text = stringResource(sign_up_done_screen_go_to_sign_in_button_text),
            modifier = Modifier.padding(top = 16.dp, bottom = 28.dp),
            enabled = true,
            containerColor = PointColor1,
            textColor = Black100,
            onClick = onNextButtonClick,
        )
    }
}

@Preview
@Composable
fun SignUpDoneScreenPreview() {
    DanjamTheme {
        SignUpDoneScreen(
            imageUri = "",
            modifier = Modifier,
            onImageTaken = {},
            onNextButtonClick = {}
        )
    }
}
