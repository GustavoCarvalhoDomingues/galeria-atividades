package com.example.appgaleria.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgaleria.data.preferences.SessionPreferences
import com.example.appgaleria.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val users: UserRepository, private val session: SessionPreferences) : ViewModel() {
    fun login(email: String, password: String, onResult: (String?) -> Unit) {
        if (email.isBlank() || password.isBlank()) { onResult("Preencha e-mail e senha."); return }
        if (!users.authenticate(email, password)) { onResult("E-mail ou senha inválidos."); return }
        viewModelScope.launch { session.setLoggedIn(true); onResult(null) }
    }
}
