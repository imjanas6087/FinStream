package com.example.finstream.BottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route : String, val screen : String, val imageResource : ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Markets : BottomNavItem("markets", "Markets", Icons.Default.Search)
    object Profile : BottomNavItem("person", "Person", Icons.Default.Person)
}
