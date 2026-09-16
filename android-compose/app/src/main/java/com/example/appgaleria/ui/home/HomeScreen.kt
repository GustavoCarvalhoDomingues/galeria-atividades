package com.example.appgaleria.ui.home

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.appgaleria.data.local.PhotoEntity

private enum class PhotoAction { CAMERA, GALLERY }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, onAbout: () -> Unit, onLoggedOut: () -> Unit) {
    val context = LocalContext.current
    val photos by viewModel.photos.collectAsStateWithLifecycle(initialValue = emptyList())
    var showSourceDialog by remember { mutableStateOf(false) }
    var pendingAction by remember { mutableStateOf<PhotoAction?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap -> bitmap?.let(viewModel::addCameraPhoto) }
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri -> uri?.let(viewModel::addGalleryPhoto) }
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) when (pendingAction) { PhotoAction.CAMERA -> cameraLauncher.launch(null); PhotoAction.GALLERY -> galleryLauncher.launch("image/*"); null -> Unit }
        pendingAction = null
    }
    fun request(action: PhotoAction) {
        pendingAction = action
        permissionLauncher.launch(if (action == PhotoAction.CAMERA) Manifest.permission.CAMERA else if (Build.VERSION.SDK_INT >= 33) Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Minhas Fotos") }, actions = {
        IconButton(onAbout) { Icon(Icons.Default.Info, "Sobre") }
        IconButton({ viewModel.logout(onLoggedOut) }) { Icon(Icons.Default.Logout, "Sair") }
    }) }, floatingActionButton = { FloatingActionButton({ showSourceDialog = true }) { Icon(Icons.Default.Add, "Adicionar foto") } }) { padding ->
        if (photos.isEmpty()) Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) { Text("Nenhuma foto adicionada.") }
        else LazyVerticalGrid(GridCells.Fixed(2), Modifier.fillMaxSize().padding(padding).padding(8.dp)) {
            items(photos, key = { it.id }) { PhotoCell(it, viewModel::remove) { sharePhoto(context, it.uri) } }
        }
    }
    if (showSourceDialog) AlertDialog(onDismissRequest = { showSourceDialog = false }, title = { Text("Adicionar foto") }, text = { Text("Escolha a origem da imagem.") },
        confirmButton = { TextButton({ showSourceDialog = false; request(PhotoAction.CAMERA) }) { Text("Abrir câmera") } },
        dismissButton = { TextButton({ showSourceDialog = false; request(PhotoAction.GALLERY) }) { Text("Escolher da galeria") } })
}

@Composable
private fun PhotoCell(photo: PhotoEntity, onRemove: (PhotoEntity) -> Unit, onShare: () -> Unit) {
    Box(Modifier.padding(4.dp)) {
        AsyncImage(model = photo.uri, contentDescription = "Foto", modifier = Modifier.fillMaxWidth().aspectRatio(1f), contentScale = ContentScale.Crop)
        IconButton({ onRemove(photo) }, Modifier.align(Alignment.TopEnd)) { Icon(Icons.Default.Delete, "Remover", tint = MaterialTheme.colorScheme.error) }
        IconButton(onShare, Modifier.align(Alignment.BottomEnd)) { Icon(Icons.Default.Share, "Compartilhar foto") }
    }
}

private fun sharePhoto(context: android.content.Context, uriString: String) {
    val file = Uri.parse(uriString).path?.let(::java.io.File) ?: return
    val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "image/*"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(intent, "Compartilhar foto"))
}
