package com.example.appgaleria.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

class ImageStorage(private val context: Context) {
    private val photoDir get() = File(context.filesDir, "photos").apply { mkdirs() }
    fun saveBitmap(bitmap: Bitmap): String {
        val file = File(photoDir, "photo_${System.currentTimeMillis()}.jpg")
        FileOutputStream(file).use { bitmap.compress(Bitmap.CompressFormat.JPEG, 90, it) }
        return Uri.fromFile(file).toString()
    }
    fun copyFrom(uri: Uri): String {
        val file = File(photoDir, "photo_${System.currentTimeMillis()}.jpg")
        context.contentResolver.openInputStream(uri)?.use { input -> file.outputStream().use(input::copyTo) }
            ?: error("Não foi possível ler a imagem selecionada.")
        return Uri.fromFile(file).toString()
    }
    fun delete(uriString: String) { Uri.parse(uriString).path?.let(::File)?.delete() }
}
