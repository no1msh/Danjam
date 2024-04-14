package com.saeongjima.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.saeongjima.designsystem.R

val wantedSans = FontFamily(
    Font(R.font.wanted_sans_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.wanted_sans_semi_bold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.wanted_sans_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.wanted_sans_regular, FontWeight.Normal, FontStyle.Normal), // Regular
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    displayMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),

    headlineLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    headlineMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),

    titleLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    titleMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    titleSmall = TextStyle(
        fontFamily = wantedSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal, // Regular
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),

    bodyLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    bodyMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal, // Regular
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    bodySmall = TextStyle(
        fontFamily = wantedSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),

    labelLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal, // Regular
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    labelMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 8.sp,
        fontWeight = FontWeight.Normal, // Regular
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
)
