package com.saeongjima.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saeongjima.designsystem.component.button.MainButton
import com.saeongjima.designsystem.extension.modifier.verticalScrollBar
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black600
import com.saeongjima.designsystem.theme.Black700
import com.saeongjima.designsystem.theme.Black800
import com.saeongjima.designsystem.theme.Black950
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.MainColor
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.designsystem.theme.White
import com.saeongjima.login.R

@Composable
fun PersonalInformationCertificationGuideRoute(
    modifier: Modifier,
    onNextButtonClick: () -> Unit
) {
    PersonalInformationCertificationGuideScreen(
        modifier = modifier,
        onNextButtonClick = onNextButtonClick
    )
}

@Composable
internal fun PersonalInformationCertificationGuideScreen(
    modifier: Modifier = Modifier,
    onNextButtonClick: () -> Unit,
) {
    val scrollState: ScrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .verticalScrollBar(scrollState, isAlwaysVisible = true)
    ) {
        Text(
            text = stringResource(R.string.personal_information_certification_guide_title),
            style = MaterialTheme.typography.displayLarge,
            color = Black950,
        )
        Text(
            text = stringResource(R.string.personal_information_certification_guide_description),
            style = MaterialTheme.typography.titleMedium.copy(lineHeight = 24.sp),
            color = Black600,
            modifier = Modifier.padding(top = 8.dp),
        )
        Text(
            text = stringResource(R.string.personal_information_certification_guide_guide_title),
            style = MaterialTheme.typography.titleLarge,
            color = Black800,
            modifier = Modifier.padding(top = 24.dp)
        )

        Image(
            painter = painterResource(id = com.saeongjima.designsystem.R.drawable.img_id_card_example),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(R.string.personal_information_certification_guide_guide),
            style = MaterialTheme.typography.titleLarge,
            color = PointColor1,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = stringResource(R.string.personal_information_certification_guide_caution),
            style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 22.sp),
            color = Black950,
            modifier = Modifier.padding(top = 8.dp)
        )
        CautionBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 44.dp)
        )
        MainButton(
            text = stringResource(R.string.personal_information_certification_guide_next_button_text),
            modifier = Modifier.padding(top = 48.dp, bottom = 28.dp),
            enabled = true,
            containerColor = MainColor,
            textColor = White,
            onClick = onNextButtonClick,
        )
    }
}

@Composable
private fun CautionBox(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(Black200),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column {
            Text(
                text = stringResource(R.string.personal_information_certification_guide_caution_box_title),
                style = MaterialTheme.typography.titleLarge,
                color = Black800,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = stringResource(R.string.personal_information_certification_guide_caution_box_description),
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                color = Black700,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PersonalInformationCertificationScreenPreview() {
    DanjamTheme {
        PersonalInformationCertificationGuideScreen(onNextButtonClick = {})
    }
}
