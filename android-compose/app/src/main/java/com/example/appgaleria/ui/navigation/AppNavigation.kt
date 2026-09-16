package com.example.appgaleria.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appgaleria.AppGaleriaApplication
import com.example.appgaleria.ui.about.AboutScreenEnhanced
import com.example.appgaleria.ui.home.HomeScreen
import com.example.appgaleria.ui.home.HomeViewModel
import com.example.appgaleria.ui.login.LoginScreen
import com.example.appgaleria.ui.login.LoginViewModel
import com.example.appgaleria.ui.register.RegisterScreen
import com.example.appgaleria.ui.register.RegisterViewModel
import kotlinx.coroutines.flow.map

private object Route { const val LOGIN = "login"; const val REGISTER = "register"; const val HOME = "home"; const val ABOUT = "about" }

@Composable
fun AppNavigation(app: AppGaleriaApplication) {
    val navController = rememberNavController()
    val loggedIn by app.sessionPreferences.isLoggedIn.map<Boolean, Boolean?> { it }.collectAsStateWithLifecycle(initialValue = null)
    if (loggedIn == null) return
    val startDestination = if (loggedIn == true) Route.HOME else Route.LOGIN

    NavHost(navController, startDestination) {
        composable(Route.LOGIN) {
            val viewModel = remember { LoginViewModel(app.userRepository, app.sessionPreferences) }
            LoginScreen(viewModel,
                onRegister = { navController.navigate(Route.REGISTER) },
                onLoggedIn = { navController.navigate(Route.HOME) { popUpTo(Route.LOGIN) { inclusive = true } } })
        }
        composable(Route.REGISTER) {
            val viewModel = remember { RegisterViewModel(app.userRepository) }
            RegisterScreen(viewModel,
                onBack = { navController.popBackStack() },
                onRegistered = { navController.popBackStack() })
        }
        composable(Route.HOME) {
            val viewModel = remember { HomeViewModel(app.photoRepository, app.sessionPreferences) }
            HomeScreen(viewModel,
                onAbout = { navController.navigate(Route.ABOUT) },
                onLoggedOut = { navController.navigate(Route.LOGIN) { popUpTo(Route.HOME) { inclusive = true } } })
        }
        composable(Route.ABOUT) { AboutScreenEnhanced(preferences = app.appPreferences, onBack = { navController.popBackStack() }) }
    }
}
