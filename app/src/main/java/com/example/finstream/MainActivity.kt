package com.example.finstream

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finstream.BottomNavigation.BottomNavItem
import com.example.finstream.BottomNavigation.BottomNavigation
import com.example.finstream.screen.DashboardScreen
import com.example.finstream.ui.theme.FinStreamTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinStreamTheme {
                FinStreamApp()
            }
        }
    }
}

@Composable
fun FinStreamApp(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ){
        composable(BottomNavItem.Home.route) {

            DashboardScreen(
                navController = navController,
                onAssetClick = { symbol ->
                    navController.navigate("detail/$symbol")
                }
            )
        }

        composable(BottomNavItem.Markets.route) {
            PlaceholderScreen(title = "Markets", navController = navController)
        }

        composable(BottomNavItem.Profile.route) {
            PlaceholderScreen(title = "Person", navController = navController)
        }

        composable("detail/{symbol}") { backStackEntry ->
            val symbol = backStackEntry.arguments?.getString("symbol") ?: "Unknown"
            PlaceholderScreen(title = symbol.uppercase(), navController = navController)
        }
    }
}

@Composable
fun PlaceholderScreen(title: String, navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigation(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = title)
        }
    }
}