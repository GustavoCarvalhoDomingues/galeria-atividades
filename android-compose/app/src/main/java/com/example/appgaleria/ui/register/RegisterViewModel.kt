package com.example.appgaleria.ui.register

import androidx.lifecycle.ViewModel
import com.example.appgaleria.data.repository.UserRepository

class RegisterViewModel(private val users: UserRepository) : ViewModel() {
    fun register(name: String, email: String, password: String): String? = when {
        name.isBlank() || email.isBlank() || password.isBlank() -> "Preencha todos os campos."
        !users.register(name, email, password) -> "Já existe um cadastro para este e-mail."
        else -> null
    }
}
