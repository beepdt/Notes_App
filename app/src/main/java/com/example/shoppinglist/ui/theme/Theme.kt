package com.example.shoppinglist.ui.theme


import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val LightColorPalette = lightColorScheme(
    primary = Color(0xFFC7EBB3),
    background = Color(0xffFCF6F1),
    secondary = Color(0xff130303),
    surface = Color(0xff323430),
    onPrimary = Color(0xFF1D1D1D),
    onSecondary = Color.Black,
    onBackground = Color(0xFFC7EBB3),
    onSurface = Color(0xFFC7EBB3),
    primaryContainer = Color(0xffFCF6F1)
)
private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF222222),
    secondary = Color(0xffEFE9E7),
    background = Color(0xff130303),
    surface = Color(0xFFC7EBB3),
    onPrimary = Color.Black,
    onSecondary = Color(0xff323430),
    onBackground = Color(0xFF2E2E2E),
    onSurface = Color(0xff47A025),
    primaryContainer = Color(0xFF2E2E2E)
)

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Settings")
val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val systemUIController = rememberSystemUiController()

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) DarkColorPalette else LightColorPalette
        }

        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    LaunchedEffect(darkTheme){
        systemUIController.setStatusBarColor(
            color = colorScheme.background,
        )
    }


    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = colorScheme.background.luminance() > 0.5
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CustomTypography,
        content = content,
    )
}