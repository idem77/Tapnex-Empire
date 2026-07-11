package com.tapnexempire.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.Icon
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
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
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

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(

        modifier = Modifier
            .padding(horizontal = 14.dp, vertical = 10.dp)
            .height(72.dp)
            .navigationBarsPadding()
            .clip(
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                )
            ),

        containerColor = Color(0xEE0B1220),

        tonalElevation = 18.dp

    ) {

        items.forEach { item ->

            val selected = currentRoute == item.route

            NavigationBarItem(

                selected = selected,

                onClick = {

                    if (currentRoute != item.route) {

                        navController.navigate(item.route) {

    popUpTo(navController.graph.startDestinationId)

    launchSingleTop = true

    restoreState = true
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

                alwaysShowLabel = false,

                colors = NavigationBarItemDefaults.colors(

                    selectedIconColor = Color(0xFFFFD54F),

                    selectedTextColor = Color(0xFFFFD54F),

                    unselectedIconColor = Color(0xFFBFC6D1),

                    unselectedTextColor = Color(0xFFBFC6D1),

                    indicatorColor = Color(0x33FFD54F)

                )

            )

        }

    }

}
