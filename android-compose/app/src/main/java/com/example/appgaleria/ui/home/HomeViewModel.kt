package com.example.appgaleria.ui.home

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgaleria.data.local.PhotoEntity
import com.example.appgaleria.data.preferences.SessionPreferences
import com.example.appgaleria.data.repository.PhotoRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val photosRepository: PhotoRepository, private val session: SessionPreferences) : ViewModel() {
    val photos = photosRepository.observePhotos()
    fun addCameraPhoto(bitmap: Bitmap) = viewModelScope.launch { photosRepository.addCameraPhoto(bitmap) }
    fun addGalleryPhoto(uri: Uri) = viewModelScope.launch { photosRepository.addGalleryPhoto(uri) }
    fun remove(photo: PhotoEntity) = viewModelScope.launch { photosRepository.delete(photo) }
    fun logout(onLoggedOut: () -> Unit) = viewModelScope.launch { session.setLoggedIn(false); onLoggedOut() }
}
