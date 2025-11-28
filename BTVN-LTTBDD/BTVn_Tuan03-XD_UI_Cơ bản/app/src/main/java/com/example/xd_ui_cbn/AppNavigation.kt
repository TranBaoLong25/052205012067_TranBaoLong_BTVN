package com.example.xd_ui_cbn

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.xd_ui_cbn.screens.HomeScreen
import com.example.xd_ui_cbn.screens.ImageDetailScreen
import com.example.xd_ui_cbn.screens.RowDetailScreen
import com.example.xd_ui_cbn.screens.TextDetailScreen
import com.example.xd_ui_cbn.screens.TextFieldDetailScreen
import com.example.xd_ui_cbn.screens.WelcomeScreen

sealed class Screen(val route: String, val title: String) {
    object Welcome : Screen("welcome", "Welcome")
    object Home : Screen("home", "UI Components List")
    object TextDetail : Screen("text_detail", "Text Detail")
    object ImageDetail : Screen("image_detail", "Images")
    object TextFieldDetail : Screen("textfield_detail", "TextField")
    object RowDetail : Screen("row_detail", "Row Layout")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.TextDetail.route) {
            TextDetailScreen(navController)
        }
        composable(Screen.ImageDetail.route) {
            ImageDetailScreen(navController)
        }
        composable(Screen.TextFieldDetail.route) {
            TextFieldDetailScreen(navController)
        }
        composable(Screen.RowDetail.route) {
            RowDetailScreen(navController)
        }
    }
}
