package com.example.appgaleria.data.repository

data class User(val name: String, val email: String, val password: String)
class UserRepository {
    private val users = mutableListOf<User>()
    fun register(name: String, email: String, password: String): Boolean {
        if (users.any { it.email.equals(email, true) }) return false
        users += User(name, email, password); return true
    }
    fun authenticate(email: String, password: String) = email.isNotBlank() && password.isNotBlank() &&
        (users.isEmpty() || users.any { it.email.equals(email, true) && it.password == password })
}
