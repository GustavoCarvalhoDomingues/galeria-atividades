package com.example.appgaleria.ui.about

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.appgaleria.data.preferences.AppPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun AboutScreenEnhanced(preferences: AppPreferences, onBack: () -> Unit) {
    val context = LocalContext.current
    val darkTheme by preferences.isDarkTheme.collectAsStateWithLifecycle(initialValue = false)
    var location by remember { mutableStateOf<Location?>(null) }
    var message by remember { mutableStateOf("Toque para permitir o acesso à localização.") }
    var online by remember { mutableStateOf(isOnline(context)) }
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        if (permissions.values.any { it }) {
            location = lastKnownLocation(context)
            message = if (location == null) "Localização indisponível. Ative o GPS e tente novamente." else ""
        } else message = "Permissão de localização negada."
    }
    DisposableEffect(context) {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) { online = isOnline(context) }
            override fun onLost(network: Network) { online = isOnline(context) }
            override fun onCapabilitiesChanged(network: Network, capabilities: NetworkCapabilities) { online = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) }
        }
        manager.registerDefaultNetworkCallback(callback)
        onDispose { manager.unregisterNetworkCallback(callback) }
    }
    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text("Sobre o AppGaleria")
        Text("Versão 1.0.0", Modifier.padding(top = 16.dp))
        Text(if (online) "Status da internet: conectado" else "Status da internet: sem conexão", Modifier.padding(top = 20.dp))
        Text("Tema escuro", Modifier.padding(top = 20.dp))
        Switch(darkTheme, onCheckedChange = { enabled -> CoroutineScope(Dispatchers.IO).launch { preferences.setDarkTheme(enabled) } })
        Text("Localização", Modifier.padding(top = 20.dp))
        if (location == null) Text(message) else {
            Text("Latitude: ${format(location!!.latitude)}")
            Text("Longitude: ${format(location!!.longitude)}")
            Text("Altitude: ${format(location!!.altitude)} m")
        }
        Button(onClick = { permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) }, Modifier.padding(top = 8.dp)) { Text("Atualizar localização") }
        Text("Termos de Uso", Modifier.padding(top = 24.dp))
        Text("Use o aplicativo de forma responsável. As fotos são armazenadas somente no seu dispositivo.")
        Text("Política de Privacidade", Modifier.padding(top = 24.dp))
        Text("Não coletamos nem enviamos suas fotos ou dados pessoais para servidores externos.")
        Button(onBack, Modifier.padding(top = 24.dp)) { Text("Voltar") }
    }
}

private fun isOnline(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = manager.getNetworkCapabilities(manager.activeNetwork) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
}

private fun lastKnownLocation(context: Context): Location? {
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) return null
    val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return listOf(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER, LocationManager.PASSIVE_PROVIDER)
        .mapNotNull { runCatching { manager.getLastKnownLocation(it) }.getOrNull() }.maxByOrNull { it.time }
}

private fun format(value: Double) = String.format(Locale.US, "%.6f", value)
