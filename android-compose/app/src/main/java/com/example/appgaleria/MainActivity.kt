package com.example.appgaleria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.appgaleria.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val app = application as AppGaleriaApplication
            val darkTheme by app.appPreferences.isDarkTheme.collectAsStateWithLifecycle(initialValue = false)
            MaterialTheme(colorScheme = if (darkTheme) androidx.compose.material3.darkColorScheme() else androidx.compose.material3.lightColorScheme()) {
                Surface { AppNavigation(app) }
            }
        }
    }
}
