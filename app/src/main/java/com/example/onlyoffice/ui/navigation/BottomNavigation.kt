package com.example.onlyoffice.ui.theme.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.onlyoffice.R


sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int, val label: String) {
    object DocumentsPage : BottomNavItem("documentsPage", R.drawable.baseline_edit_document_24, "Documents")
    object RoomsPage : BottomNavItem("roomsPage", R.drawable.baseline_checkroom_24, "Rooms")
    object TrashPage : BottomNavItem("trashPage", R.drawable.baseline_restore_from_trash_24, "Trash")
    object ProfilePage : BottomNavItem("profilePage", R.drawable.baseline_account_circle_24, "Profile")
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.DocumentsPage,
        BottomNavItem.RoomsPage,
        BottomNavItem.TrashPage,
        BottomNavItem.ProfilePage,
    )

    NavigationBar(
        containerColor = Color.LightGray,
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Box{
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = item.label,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                label = { Text(
                    text = item.label
                ) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}