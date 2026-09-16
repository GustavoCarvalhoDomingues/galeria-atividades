package com.example.appgaleria.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("session")
class SessionPreferences(private val context: Context) {
    private val loggedInKey = booleanPreferencesKey("logged_in")
    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { it[loggedInKey] ?: false }
    suspend fun setLoggedIn(value: Boolean) { context.dataStore.edit { it[loggedInKey] = value } }
}
