package com.example.onlyoffice.ui.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlyoffice.pages.AuthPage
import com.example.onlyoffice.pages.DocumentsPage
import com.example.onlyoffice.pages.ProfilePage
import com.example.onlyoffice.pages.RoomsPage
import com.example.onlyoffice.pages.TrashPage

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var showBottomNavigation by remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = { if(showBottomNavigation) BottomNavigationBar(navController = navController) },
        modifier = Modifier.navigationBarsPadding()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "authPage",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(BottomNavItem.DocumentsPage.route) { DocumentsPage() { showBottomNavigation = true } }
            composable(BottomNavItem.RoomsPage.route) { RoomsPage() { showBottomNavigation = true } }
            composable(BottomNavItem.TrashPage.route) { TrashPage() { showBottomNavigation = true } }
            composable(BottomNavItem.ProfilePage.route) { ProfilePage() { showBottomNavigation = true } }
            composable("authPage") { AuthPage() { showBottomNavigation = false } }
        }
    }
}