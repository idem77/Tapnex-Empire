NavHost(navController = navController, startDestination = "splash") {
            composable("splash") { SplashScreen(navController) }
                composable("home") { HomeScreen(navController) }
}
}