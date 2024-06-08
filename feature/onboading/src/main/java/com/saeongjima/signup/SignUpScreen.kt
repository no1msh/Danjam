package com.saeongjima.signup

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.extension.modifier.pagerFadeTransition
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.PointColor1
import com.saeongjima.signup.personalinformation.OnboardingTopAppBar
import com.saeongjima.signup.personalinformation.PersonalInformationRoute
import com.saeongjima.signup.signininformation.SignInInformationRoute
import com.saeongjima.signup.signupdone.SignUpDoneRoute
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
) {
    SignUpScreen(
        modifier = modifier,
        onCloseClick = onCloseClick,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SignUpScreen(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val pagerState = rememberPagerState(pageCount = { SignUpProgress.entries.size })
    val coroutineScope = rememberCoroutineScope()

    Column {
        OnboardingTopAppBar(
            leadingIcon = if (pagerState.currentPage == 0 || !pagerState.canScrollForward) {
                null
            } else {
                R.drawable.ic_back_24
            },
            trailingIcon = R.drawable.ic_exit_24,
            leadingIconDescription = null,
            trailingIconDescription = null,
            onLeadingIconClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(pagerState.currentPage - 1)
                }
            },
            onTrailingIconClick = onCloseClick,
            title = if (pagerState.currentPage == 0) "약관 동의" else null,
        )
        Column(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
            if (pagerState.currentPage != 0) {
                DanJamProgressBar(
                    currentPageIndex = (pagerState.currentPage).toFloat(),
                    totalPageCount = SignUpProgress.entries.size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 12.dp),
                )
            } else {
                Spacer(modifier = Modifier.height(8.dp))
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                userScrollEnabled = false,
                verticalAlignment = Alignment.Top,
            ) { page ->
                val modifierWithDefaultPadding = Modifier.padding(
                    top = 40.dp,
                    start = 24.dp,
                    end = 24.dp,
                )
                val onNextButtonClick: () -> Unit = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }

                Box(modifier = Modifier.pagerFadeTransition(page, pagerState)) {
                    when (page) {
                        0 -> AgreeRoute(
                            onNextButtonClick = onNextButtonClick,
                            modifier = Modifier.padding(horizontal = 24.dp),
                        )

                        1 -> PersonalInformationRoute(
                            onNextButtonClick = { personalInformationUiState ->
                                viewModel.updatePersonalInformation(personalInformationUiState)
                                onNextButtonClick()
                            },
                            modifier = modifierWithDefaultPadding,
                        )

                        2 -> PersonalInformationCertificationGuideRoute(
                            onNextButtonClick = onNextButtonClick,
                            modifier = modifierWithDefaultPadding,
                        )

                        3 -> PersonalInformationCertificationRoute(
                            onNextButtonClick = onNextButtonClick,
                            modifier = modifierWithDefaultPadding,
                        )

                        4 -> UniversityInformationRoute(
                            onNextButtonClick = onNextButtonClick,
                            modifier = modifierWithDefaultPadding,
                        )

                        5 -> SignInInformationRoute(
                            onNextButtonClick = { signInInformationUiState ->
                                viewModel.updateSignInInformation(signInInformationUiState)
                                onNextButtonClick()
                            },
                            modifier = modifierWithDefaultPadding,
                        )

                        6 -> UniversityCertificationRoute(
                            onNextButtonClick = onNextButtonClick,
                            modifier = modifierWithDefaultPadding,
                        )

                        7 -> SignUpDoneRoute(modifier = modifierWithDefaultPadding)
                    }
                }
            }
        }
    }
}

@Composable
fun DanJamProgressBar(
    currentPageIndex: Float,
    totalPageCount: Int,
    modifier: Modifier = Modifier,
) {
    val progress by animateFloatAsState(
        targetValue = (currentPageIndex + 1) / totalPageCount.toFloat(),
        label = "pageProgress",
    )

    LinearProgressIndicator(
        progress = { progress },
        color = PointColor1,
        trackColor = Black200,
        modifier = modifier.height(8.dp),
        strokeCap = StrokeCap.Round,
    )
}

enum class SignUpProgress {
    Agree,
    PersonalInformation,
    PersonalCertificationGuide,
    PersonalCertification,
    UniversityInformation,
    SignInInformation,
    UniversityCertification,
    SignUpDone,
}

@Preview
@Composable
private fun SignUpPreview() {
    DanjamTheme {
        SignUpScreen(
            onCloseClick = {},
        )
    }
}
