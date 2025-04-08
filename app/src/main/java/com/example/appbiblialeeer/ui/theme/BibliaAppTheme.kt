package com.example.appbiblialeeer.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BibliaAppTheme(content: @Composable () -> Unit) {
    val darkColors = darkColorScheme(
        primary = Color(0xFF000000),
        onPrimary = Color.White,
        primaryContainer = Color(0xFF1E1E1E),
        onPrimaryContainer = Color.White,
        secondary = Color(0xFF2CCFBA),
        onSecondary = Color.Black,
        background = Color(0xFF000000),
        onBackground = Color.White,
        surface = Color(0xFF1E1E1E),
        onSurface = Color.White
    )

    val customTypography = Typography(
        titleLarge = androidx.compose.ui.text.TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 30.sp,
            letterSpacing = 0.5.sp,
            color = Color.White
        ),
        titleMedium = androidx.compose.ui.text.TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 28.sp,
            letterSpacing = 0.4.sp,
            color = Color.White
        ),
        bodyLarge = androidx.compose.ui.text.TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 32.sp, // Aumentado para mejor lectura
            letterSpacing = 0.5.sp,
            color = Color.White
        ),
        bodyMedium = androidx.compose.ui.text.TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 26.sp, // Ligeramente más aire
            letterSpacing = 0.3.sp,
            color = Color.White
        ),
        labelLarge = androidx.compose.ui.text.TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 22.sp, // Más claridad
            letterSpacing = 0.4.sp,
            color = Color.White
        )
    )

    MaterialTheme(
        colorScheme = darkColors,
        typography = customTypography,
        content = content
    )
}
