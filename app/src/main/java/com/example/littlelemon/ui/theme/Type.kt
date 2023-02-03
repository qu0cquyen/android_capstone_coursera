package com.example.littlelemon.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val Markazi = FontFamily(
        Font(R.font.markazitext_regular, FontWeight.Bold),
)

val Karla = FontFamily(
        Font(R.font.karla_regular, FontWeight.Normal),
)


// Set of Material typography styles to start with
val Typography = Typography(
        h1 = TextStyle(
                fontFamily = Markazi,
                fontSize = 24.sp
        ),
        body1 = TextStyle(
                fontFamily = Karla,
                fontSize = 16.sp
        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)