package com.saeongjima.designsystem.theme

import androidx.compose.material3.Typography
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
    ),
    displayMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
    ),

    headlineLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    headlineMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
    ),

    titleLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    titleMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
    ),
    titleSmall = TextStyle(
        fontFamily = wantedSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal, // Regular
    ),

    bodyLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal, // Regular
    ),
    bodyMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
    ),

    labelLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal, // Regular
    ),
    labelMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 8.sp,
        fontWeight = FontWeight.Normal, // Regular
    ),
)
