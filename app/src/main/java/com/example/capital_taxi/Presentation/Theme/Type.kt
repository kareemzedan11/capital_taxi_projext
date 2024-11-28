package com.example.app.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R


// Custom font family
val CustomFontFamily = FontFamily(

)

// Typography styles
val AppTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = CustomFontFamily,
        fontSize = 30.sp
    ),
    titleMedium = TextStyle(
        fontFamily = CustomFontFamily,
        fontSize = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = CustomFontFamily,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = CustomFontFamily,
        fontSize = 14.sp
    )
)
