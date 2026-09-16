package com.example.appgaleria

import android.app.Application
import com.example.appgaleria.data.local.AppDatabase
import com.example.appgaleria.data.preferences.SessionPreferences
import com.example.appgaleria.data.preferences.AppPreferences
import com.example.appgaleria.data.repository.PhotoRepository
import com.example.appgaleria.data.repository.UserRepository

class AppGaleriaApplication : Application() {
    val database by lazy { AppDatabase.create(this) }
    val photoRepository by lazy { PhotoRepository(database.photoDao(), this) }
    val userRepository = UserRepository()
    val sessionPreferences by lazy { SessionPreferences(this) }
    val appPreferences by lazy { AppPreferences(this) }
}
