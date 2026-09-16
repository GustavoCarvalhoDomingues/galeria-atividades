package com.example.appgaleria.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.appDataStore by preferencesDataStore("app_preferences")

class AppPreferences(private val context: Context) {
    private val darkThemeKey = booleanPreferencesKey("dark_theme")

    val isDarkTheme: Flow<Boolean> = context.appDataStore.data.map { it[darkThemeKey] ?: false }

    suspend fun setDarkTheme(enabled: Boolean) {
        context.appDataStore.edit { it[darkThemeKey] = enabled }
    }
}
