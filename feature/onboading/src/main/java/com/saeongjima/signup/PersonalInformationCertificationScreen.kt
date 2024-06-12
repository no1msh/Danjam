package com.saeongjima.signup

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.danjam.context.getTempPngFileUri
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.component.PhotoSelectGuideBox
import com.saeongjima.designsystem.component.button.CloseUpIconButton
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.component.dialog.PhotoSourceSelectDialog
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black700
import com.saeongjima.designsystem.theme.Black800
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.designsystem.theme.White

@Composable
fun PersonalInformationCertificationRoute(
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    signUpViewModel: SignUpViewModel = hiltViewModel(),
) {
    val idCardImageUri by signUpViewModel.idCardImageUri.collectAsStateWithLifecycle()

    PersonalInformationCertificationScreen(
        idCardImageUri = idCardImageUri,
        onImageTaken = signUpViewModel::updateIdCardImageUri,
        onNextButtonClick = onNextButtonClick,
        modifier = modifier
    )
}

@Composable
fun PersonalInformationCertificationScreen(
    idCardImageUri: String,
    onImageTaken: (String) -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isOpenPhotoGetterSelectDialog by rememberSaveable { mutableStateOf(false) }
    var isOpenPhotoCloseUpDialog by rememberSaveable { mutableStateOf(false) }
    var isOpenCheckAgainDialog by rememberSaveable { mutableStateOf(false) }

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
            uri = Uri.parse(idCardImageUri),
            contentDescription = stringResource(com.saeongjima.login.R.string.personal_information_certification_image_description),
            onDismissRequest = { isOpenPhotoCloseUpDialog = false }
        )
    }

    if (isOpenCheckAgainDialog) {
        CheckAgainDialog(
            onDismissRequest = { isOpenCheckAgainDialog = false },
            onNextButtonClick = onNextButtonClick
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(com.saeongjima.login.R.string.personal_information_certification_title),
            style = MaterialTheme.typography.displayLarge,
            color = Black950,
        )

        PhotoSelectGuideBox(
            onClick = { isOpenPhotoGetterSelectDialog = true },
            modifier = Modifier.padding(top = 44.dp),
            description = stringResource(com.saeongjima.login.R.string.personal_information_certification_image_guide_description),
        )

        if (idCardImageUri.isNotEmpty()) {
            ImageBox(
                uri = Uri.parse(idCardImageUri),
                onCloseUpClick = { isOpenPhotoCloseUpDialog = true },
                contentDescription = stringResource(com.saeongjima.login.R.string.personal_information_certification_image_description),
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .weight(1f),
            )
            MainButton(
                text = stringResource(com.saeongjima.login.R.string.personal_information_certification_select_another_photo),
                containerColor = MainColor,
                textColor = White,
            ) {
                isOpenPhotoGetterSelectDialog = true
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }

        MainButton(
            text = stringResource(R.string.main_button_text_next),
            modifier = Modifier.padding(top = 16.dp, bottom = 28.dp),
            enabled = idCardImageUri.isNotEmpty(),
            containerColor = PointColor1,
            textColor = Black100,
        ) {
            isOpenCheckAgainDialog = true
        }
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

@Composable
fun FullAsyncImageDialog(
    uri: Uri?,
    contentDescription: String?,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0.8f)
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.layout_default_padding),
                    vertical = 12.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = uri,
                contentDescription = contentDescription,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
            )
            IconButton(
                onClick = { onDismissRequest() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_exit_24),
                    contentDescription = stringResource(com.saeongjima.login.R.string.personal_information_certification_close_full_image),
                    modifier = modifier.size(36.dp),
                    tint = Black700,
                )
            }
        }
    }
}

@Composable
private fun CheckAgainDialog(
    onDismissRequest: () -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(Black100, RoundedCornerShape(4.dp))
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(role = Role.Button, onClick = onDismissRequest)
                    .align(Alignment.End)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_exit_24),
                    contentDescription = stringResource(id = com.saeongjima.login.R.string.personal_information_certification_close_full_image),
                    tint = Black700,
                    modifier = Modifier.size(28.dp)
                )
            }
            Text(
                text = stringResource(com.saeongjima.login.R.string.personal_information_certification_check_again_id_card_number_covered),
                style = MaterialTheme.typography.titleLarge.copy(lineHeight = 24.sp),
                color = Black800,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 20.dp, bottom = 28.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.img_jami_124),
                contentDescription = null
            )
            MainButton(
                text = stringResource(com.saeongjima.login.R.string.personal_information_certification_check_again_sure),
                textStyle = MaterialTheme.typography.titleLarge,
                containerColor = MainColor,
                textColor = White,
                modifier = Modifier.padding(
                    top = 24.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 28.dp
                ),
                onClick = {
                    onDismissRequest()
                    onNextButtonClick()
                }
            )
        }
    }
}

@Preview
@Composable
private fun PersonalInformationCertificationScreenPreview() {
    DanjamTheme {
        PersonalInformationCertificationScreen(
            idCardImageUri = "",
            onImageTaken = {},
            onNextButtonClick = {})
    }
}
