package com.example.aptoidebymashalriaz.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.aptoidebymashalriaz.ui.theme.AptoideColor.AptoideError
import com.example.aptoidebymashalriaz.ui.theme.AptoideColor.AptoidePrimary
import com.example.aptoidebymashalriaz.ui.theme.AptoideColor.AptoidePrimaryVariant
import com.example.aptoidebymashalriaz.ui.theme.AptoideColor.AptoideSecondary

val LightColorScheme = lightColorScheme(
    primary = AptoidePrimary,
    onPrimary = Color.White,
    primaryContainer = AptoidePrimaryVariant,
    secondary = AptoideSecondary,
    onSecondary = Color.White,
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF212121),
    surface = Color(0xFFFAFAFA),
    onSurface = Color(0xFF212121),
    outline = Color(0xFFE0E0E0),
    error = AptoideError,
    onError = Color.White
)

@Composable
fun AptoideTheme(
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicLightColorScheme(LocalContext.current)
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}