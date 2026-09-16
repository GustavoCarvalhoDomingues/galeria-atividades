package com.example.appgaleria.ui.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, onBack: () -> Unit, onRegistered: () -> Unit) {
    var name by remember { mutableStateOf("") }; var email by remember { mutableStateOf("") }; var password by remember { mutableStateOf("") }; var error by remember { mutableStateOf<String?>(null) }
    Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Cadastro"); OutlinedTextField(name, { name = it }, Modifier.fillMaxWidth(), label = { Text("Nome") })
        OutlinedTextField(email, { email = it }, Modifier.fillMaxWidth(), label = { Text("E-mail") })
        OutlinedTextField(password, { password = it }, Modifier.fillMaxWidth(), label = { Text("Senha") }, visualTransformation = PasswordVisualTransformation())
        error?.let { Text(it, color = androidx.compose.material3.MaterialTheme.colorScheme.error) }
        Button({ error = viewModel.register(name, email, password); if (error == null) onRegistered() }, Modifier.fillMaxWidth().padding(top = 16.dp)) { Text("Salvar") }
        Button(onBack, Modifier.fillMaxWidth()) { Text("Voltar") }
    }
}
