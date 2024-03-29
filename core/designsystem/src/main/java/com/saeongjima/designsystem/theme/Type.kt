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
    // H1
    displayLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    ),

    // H2
    displayMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
    ),

    // H3
    headlineLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
    ),

    // H4
    headlineMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
    ),

    // H5
    headlineSmall = TextStyle(
        fontFamily = wantedSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
    ),

    // Body1
    bodyLarge = TextStyle(
        fontFamily = wantedSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
    ),

    // Body2
    bodyMedium = TextStyle(
        fontFamily = wantedSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal, // Regular
    ),
)
