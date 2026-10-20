package com.example.finstream.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// 1. Force our custom dark palette
private val AppColorScheme = darkColorScheme(
    primary = BullishGreen,
    secondary = BearishRed,
    background = AppBackground,
    surface = AppSurface,
    onBackground = PrimaryText,
    onSurface = PrimaryText,
    surfaceVariant = AppSurface
)

@Composable
fun FinStreamTheme(
    // 2. We remove the dynamicColor and light theme toggle to force our dark UI
    content: @Composable () -> Unit
) {
    val view = LocalView.current

    // 3. This block ensures the top status bar (battery, wifi) blends into your app background
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = AppColorScheme.background.toArgb()
            window.navigationBarColor = AppColorScheme.background.toArgb()

            // This makes the battery and wifi icons white so they are visible on the dark background
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography, // Ensure this matches the name in your Type.kt file
        content = content
    )
}