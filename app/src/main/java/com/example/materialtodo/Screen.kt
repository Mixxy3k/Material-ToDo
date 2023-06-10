package com.example.materialtodo

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object AddTaskScreen : Screen(route = "add_task_screen")
    object RegisterScreen: Screen(route = "register_screen")
    object LoginScreen: Screen(route = "login_screen")
}