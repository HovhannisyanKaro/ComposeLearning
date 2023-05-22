package com.composeappdemo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationForBottomNavigationWithBadges(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }

        composable("chat") {
            ChatScreen()
        }

        composable("settings") {
            SettingsScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(items: List<BottomNavItem>, navController: NavController, modifier: Modifier = Modifier, onItemClick: (BottomNavItem) -> Unit) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(modifier = modifier, backgroundColor = Color.DarkGray, elevation = 5.dp) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            //selected = item.route == navController.currentDestination?.route սա չի աշխատի, որովհետև ամեն անգամ
            // երբ որ փոխվի routte ը recompose չի լինելու, այսինքն սխալ ա ցույց տալու
            BottomNavigationItem(selected = selected, onClick = {
                onItemClick(item)
            }, icon = {
                Column(horizontalAlignment = CenterHorizontally) {
                    if (item.badgeCount > 0) {
                        BadgedBox(badge = {
                            Text(text = item.badgeCount.toString())
                        }) {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }
                    } else {
                        Icon(imageVector = item.icon, contentDescription = item.name)
                    }
                    if (selected) {
                        Text(text = item.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                    }
                }
            }, selectedContentColor = Color.Green, unselectedContentColor = Color.Gray)
        }
    }
}

data class BottomNavItem(val name: String = "Home", val route: String, val icon: ImageVector = Icons.Default.Home, var badgeCount: Int = 0) {

}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Home Screen")
    }
}

@Composable
fun ChatScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Chat Screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Settings Screen")
    }
}