package com.tapnexempire.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(

    val route: String,

    val icon:
        androidx.compose.ui.graphics.vector.ImageVector,

    val label: String
)

@Composable
fun BottomNavBar(
    navController: NavController
) {

    val items = listOf(

        BottomNavItem(
            Routes.HOME,
            Icons.Default.Home,
            "Home"
        ),

        BottomNavItem(
            Routes.TOURNAMENTS,
            Icons.Default.EmojiEvents,
            "Battle"
        ),

        BottomNavItem(
            Routes.TASKS,
            Icons.Default.Task,
            "Tasks"
        ),

        BottomNavItem(
            Routes.WALLET,
            Icons.Default.AccountBalanceWallet,
            "Wallet"
        ),

    BottomNavItem(
    Routes.PROFILE,
    Icons.Default.Person,
    "Profile"
)
    )

    val navBackStackEntry by
        navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry?.destination?.route

    NavigationBar(

        modifier = Modifier
    .height(80.dp)
    .navigationBarsPadding()
    .clip(
        RoundedCornerShape(
            topStart = 28.dp,
            topEnd = 28.dp
        )
    ),

        containerColor =
            Color(0xFF12141A),

        tonalElevation = 12.dp
    ) {

        items.forEach { item ->

            val selected =
                currentRoute == item.route

            NavigationBarItem(

                selected = selected,

                onClick = {

                    if (currentRoute != item.route) {

                        navController.navigate(
                            item.route
                        ) {

                            popUpTo(navController.graph.startDestinationId)

                            launchSingleTop = true
                        }
                    }
                },

                icon = {

                    Icon(

                        imageVector = item.icon,

                        contentDescription = item.label
                    )
                },

                label = {

                    Text(
                        text = item.label
                    )
                },

                colors =
                    NavigationBarItemDefaults.colors(

                        selectedIconColor =
                            Color(0xFF00D1FF),

                        selectedTextColor =
                            Color(0xFF00D1FF),

                        unselectedIconColor =
                            Color.LightGray,

                        unselectedTextColor =
                            Color.LightGray,

                        indicatorColor =
                            Color(0x2200D1FF)
                    )
            )
        }
    }
}
