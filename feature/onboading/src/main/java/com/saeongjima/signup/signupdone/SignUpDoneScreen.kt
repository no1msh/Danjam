package com.saeongjima.signup.signupdone

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.danjam.context.getTempPngFileUri
import com.danjam.context.processAndAdjustImage
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.component.dialog.PhotoSourceSelectDialog
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black300
import com.saeongjima.designsystem.theme.Black500
import com.saeongjima.designsystem.theme.Black900
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.designsystem.theme.White
import com.saeongjima.login.R.string.sign_up_done_screen_department_title
import com.saeongjima.login.R.string.sign_up_done_screen_description
import com.saeongjima.login.R.string.sign_up_done_screen_go_to_sign_in_button_text
import com.saeongjima.login.R.string.sign_up_done_screen_id_title
import com.saeongjima.login.R.string.sign_up_done_screen_profile_image_change_button_description
import com.saeongjima.login.R.string.sign_up_done_screen_profile_image_description
import com.saeongjima.login.R.string.sign_up_done_screen_student_number_title
import com.saeongjima.login.R.string.sign_up_done_screen_title
import com.saeongjima.signup.FullAsyncImageDialog
import com.saeongjima.signup.SignUpViewModel

@Composable
fun SignUpDoneRoute(
    modifier: Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val signUpDoneUiState by viewModel.signUpDoneUiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    SignUpDoneScreen(
        modifier = modifier,
        signUpUiState = signUpDoneUiState,
        onImageTaken = { imageUri ->
            viewModel.updateProfileImageUri(
                value = imageUri,
                uriToFile = { uri ->
                    processAndAdjustImage(context = context, uri = uri)
                }
            )
        },
        onNextButtonClick = { /* TODO: 홈 화면 이동 로직*/ }
    )
}

@Composable
fun SignUpDoneScreen(
    modifier: Modifier = Modifier,
    signUpUiState: SignUpDoneUiState,
    onImageTaken: (String) -> Unit,
    onNextButtonClick: () -> Unit,
) {
    var isOpenPhotoGetterSelectDialog by rememberSaveable { mutableStateOf(false) }
    var isOpenPhotoCloseUpDialog by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        setOf(
            R.drawable.img_default_profile_1,
            R.drawable.img_default_profile_2,
            R.drawable.img_default_profile_3,
            R.drawable.img_default_profile_4,
        ).random().also {
            onImageTaken(it.getResourceUri(context))
        }
    }

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
            uri = Uri.parse(signUpUiState.profileImageUri),
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
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(signUpUiState.profileImageUri)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .background(Black300, shape = CircleShape)
                        .clip(CircleShape)
                        .clickable { isOpenPhotoCloseUpDialog = true }
                        .size(200.dp)
                        .padding(if (signUpUiState.profileImageUri.isEmpty()) 68.dp else 0.dp),
                )
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(White, CircleShape)
                        .clip(CircleShape)
                        .align(Alignment.BottomEnd)
                        .clickable { isOpenPhotoGetterSelectDialog = true },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit_24),
                        contentDescription = stringResource(
                            sign_up_done_screen_profile_image_change_button_description
                        ),
                        tint = Black100,
                        modifier = Modifier.size(30.dp),
                    )
                }
            }

            Column(modifier = Modifier.padding(top = 48.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(sign_up_done_screen_id_title),
                        style = MaterialTheme.typography.headlineLarge,
                        color = Black900,
                        modifier = Modifier.width(80.dp),
                    )
                    Text(
                        text = signUpUiState.id,
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
                        text = signUpUiState.department,
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
                        text = signUpUiState.entryYear.drop(2) + "학번",
                        style = MaterialTheme.typography.titleLarge,
                        color = White,
                    )
                }
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

fun @receiver:DrawableRes Int.getResourceUri(context: Context): String {
    return context.resources.let { resources ->
        Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(this))
            .appendPath(resources.getResourceTypeName(this))
            .appendPath(resources.getResourceEntryName(this))
            .build()
            .toString()
    }
}

@Preview
@Composable
fun SignUpDoneScreenPreview() {
    DanjamTheme {
        SignUpDoneScreen(
            signUpUiState = SignUpDoneUiState(),
            modifier = Modifier,
            onImageTaken = {},
            onNextButtonClick = {}
        )
    }
}
