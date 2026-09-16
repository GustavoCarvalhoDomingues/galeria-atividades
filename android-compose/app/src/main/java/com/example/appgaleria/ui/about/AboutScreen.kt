package com.example.appgaleria.ui.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text("Sobre o AppGaleria"); Text("Versão 1.0.0", Modifier.padding(top = 16.dp))
        Text("Termos de Uso", Modifier.padding(top = 24.dp)); Text("Use o aplicativo de forma responsável. As fotos são armazenadas somente no seu dispositivo.")
        Text("Política de Privacidade", Modifier.padding(top = 24.dp)); Text("Não coletamos nem enviamos suas fotos ou dados pessoais para servidores externos.")
        Button(onBack, Modifier.padding(top = 24.dp)) { Text("Voltar") }
    }
}
