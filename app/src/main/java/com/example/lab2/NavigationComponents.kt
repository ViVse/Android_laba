package com.example.lab2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object Home : Screen("bookList", "Головна", Icons.Filled.Home)
    data object AddBook : Screen("addBook", "Додати книгу", Icons.Filled.Add)
    data object Profile : Screen("userProfile", "Профіль", Icons.Filled.AccountCircle)
}

val menuItems = listOf(Screen.Home, Screen.AddBook, Screen.Profile)

@Composable
fun BottomNavigationComponent(navController: NavController) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        menuItems.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationRailComponent(navController: NavController, modifier: Modifier) {
    NavigationRail(modifier = modifier) {
        val currentRoute = currentRoute(navController)
        menuItems.forEach { screen ->
            NavigationRailItem(
                modifier = Modifier.fillMaxWidth(),
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
fun PermanentNavigationDrawerComponent(navController: NavController, modifier: Modifier) {
    val currentRoute = currentRoute(navController)
    Column(modifier = modifier) {
        menuItems.forEach { screen ->
            Row(modifier = Modifier
                .clickable { if (currentRoute != screen.route) navController.navigate(screen.route) }
                .padding(16.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(screen.icon, contentDescription = screen.title)
                Spacer(Modifier.width(16.dp))
                Text(screen.title)
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
