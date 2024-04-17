package com.saeongjima.signup

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.component.button.DanjamButton
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.designsystem.theme.PointColor1
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { 6 })
    val coroutineScope = rememberCoroutineScope()
    var isButtonEnabled by rememberSaveable {
        mutableStateOf(true)
    }

    Column {
        OnboardingTopAppBar(
            leadingIcon = if (pagerState.currentPage == 0) {
                null
            } else {
                com.saeongjima.designsystem.R.drawable.ic_back_24
            },
            trailingIcon = com.saeongjima.designsystem.R.drawable.ic_exit_24,
            leadingIconDescription = null,
            trailingIconDescription = null,
            onLeadingIconClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            },
            onTrailingIconClick = onCloseClick,
            title = if (pagerState.currentPage == 0) "약관 동의" else null,
        )
        Column(
            modifier = modifier.background(MaterialTheme.colorScheme.background),
        ) {
            if (pagerState.currentPage != 0) {
                DanJamProgressBar(
                    currentPageIndex = (pagerState.currentPage - 1).toFloat(),
                    totalPageCount = 5,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 12.dp),
                )
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                userScrollEnabled = false,
                verticalAlignment = Alignment.Top,
            ) { page ->
                when (page) {
                    0 -> {
                        AgreeScreen(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            enableButton = { it: Boolean -> isButtonEnabled = it })
                    }

                    1 -> PersonalInformationScreen(
                        modifier = Modifier.padding(
                            top = 40.dp,
                            start = 24.dp,
                            end = 24.dp,
                        ),
                    )

                    2 -> UniversityInformationScreen(
                        modifier = Modifier.padding(
                            top = 40.dp,
                            start = 24.dp,
                            end = 24.dp,
                        ),
                    )

                    3 -> SignInInformationScreen(
                        modifier = Modifier.padding(
                            top = 40.dp,
                            start = 24.dp,
                            end = 24.dp,
                        ),
                    )

                    4 -> UniversityCertificationScreen(
                        modifier = Modifier.padding(
                            top = 40.dp,
                            start = 24.dp,
                            end = 24.dp,
                        ),
                    )

                    5 -> SignUpDoneScreen(
                        modifier = Modifier.padding(
                            top = 40.dp,
                            start = 24.dp,
                            end = 24.dp,
                        ),
                    )
                }
            }

            DanjamButton(
                text = if (pagerState.currentPage == 5) "단잠메이트 만나러 가기!" else "다음",
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 12.dp,
                    bottom = 28.dp,
                ),
                enabled = isButtonEnabled
            ) {
                coroutineScope.launch {
                    if (pagerState.currentPage == 5) {
                    } else {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
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

@Preview
@Composable
private fun SignUpPreview() {
    DanjamTheme {
        SignUpScreen(modifier = Modifier.fillMaxSize(), onCloseClick = {})
    }
}
