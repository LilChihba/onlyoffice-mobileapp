package com.example.onlyoffice.ui.theme.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlyoffice.pages.DocumentsPage
import com.example.onlyoffice.pages.ProfilePage
import com.example.onlyoffice.pages.RoomsPage
import com.example.onlyoffice.pages.TrashPage

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        modifier = Modifier.navigationBarsPadding()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.DocumentsPage.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(BottomNavItem.DocumentsPage.route) { DocumentsPage() }
            composable(BottomNavItem.RoomsPage.route) { RoomsPage() }
            composable(BottomNavItem.TrashPage.route) { TrashPage() }
            composable(BottomNavItem.ProfilePage.route) { ProfilePage() }
        }
    }
}