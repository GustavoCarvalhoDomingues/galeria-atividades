package com.example.appgaleria.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.example.appgaleria.data.local.PhotoDao
import com.example.appgaleria.data.local.PhotoEntity
import com.example.appgaleria.utils.ImageStorage

class PhotoRepository(private val dao: PhotoDao, context: Context) {
    private val storage = ImageStorage(context)
    fun observePhotos() = dao.observeAll()
    suspend fun addCameraPhoto(bitmap: Bitmap) = dao.insert(PhotoEntity(uri = storage.saveBitmap(bitmap)))
    suspend fun addGalleryPhoto(uri: Uri) = dao.insert(PhotoEntity(uri = storage.copyFrom(uri)))
    suspend fun delete(photo: PhotoEntity) { storage.delete(photo.uri); dao.delete(photo) }
}
