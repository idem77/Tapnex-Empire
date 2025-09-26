@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash) {

        // Splash
        composable(Screen.Splash) {
            SplashScreen(
                onTimeout = { navController.navigate(Screen.Login) }
            )
        }

        // Login
        composable(Screen.Login) {
            LoginScreen(
                onLoginClick = { navController.navigate(Screen.OtpVerification) },
                onSignupClick = { navController.navigate(Screen.Signup) }
            )
        }

        // Signup
        composable(Screen.Signup) {
            SignupScreen(
                onSignupClick = { navController.navigate(Screen.OtpVerification) },
                onLoginClick = { navController.popBackStack() }
            )
        }

        // OTP Verification
        composable(Screen.OtpVerification) {
            OtpVerificationScreen(
                phoneNumber = "",
                onVerifyClick = { navController.navigate(Screen.Home) }
            )
        }

        // Home
        composable(Screen.Home) {
            HomeScreen(
                coins = 120,
                gameList = listOf(
                    Game("Ludo", "ludo_image"),
                    Game("Carrom", "carrom_image"),
                    Game("Chess", "chess_image")
                ),
                onGameClick = { /* TODO */ }
            )
        }

        // Wallet
        composable(Screen.Wallet) {
            WalletScreen(
                depositBalance = 500,
                withdrawableBalance = 200,
                referralRewards = listOf(
                    "Bonus 50 Coins" to 50,
                    "Task Reward 20" to 20
                ),
                onDepositClick = { navController.navigate(Screen.Deposit) },
                onWithdrawClick = { navController.navigate(Screen.Withdraw) },
                onTransactionHistoryClick = { navController.navigate(Screen.TransactionHistory) }
            )
        }

        // Deposit
        composable(Screen.Deposit) {
            DepositScreen(onDepositClick = {}, currentDepositBalance = 500)
        }

        // Withdraw
        composable(Screen.Withdraw) {
            WithdrawScreen(onWithdrawClick = {}, currentWithdrawableBalance = 200)
        }

        // Transaction History
        composable(Screen.TransactionHistory) {
            TransactionHistoryScreen(
                transactions = listOf(
                    Transaction("Deposit", 500),
                    Transaction("Withdraw", -200),
                    Transaction("Reward", 50)
                )
            )
        }

        // Profile
        composable(Screen.Profile) {
            ProfileScreen(
                userName = "Queen 👑",
                onEditProfileClick = { navController.navigate(Screen.EditProfile) },
                onSettingsClick = { navController.navigate(Screen.Settings) }
            )
        }

        // Settings
        composable(Screen.Settings) {
            SettingsScreen(
                notificationsEnabled = true,
                onNotificationToggle = {},
                onHelpClick = {}
            )
        }

        // Edit Profile
        composable(Screen.EditProfile) {
            EditProfileScreen(currentName = "Queen 👑", onSaveClick = {})
        }

        // Tournament List
        composable(Screen.TournamentList) {
            TournamentListScreen(
                tournaments = listOf(
                    Tournament("Ludo Clash", 50, 1000),
                    Tournament("Carrom King", 20, 500)
                ),
                onTournamentClick = { navController.navigate(Screen.TournamentDetail) }
            )
        }

        // Tournament Detail
        composable(Screen.TournamentDetail) {
            TournamentDetailScreen(
                tournament = Tournament("Ludo Clash", 50, 1000),
                onJoinClick = {}
            )
        }

        // My Tournaments
        composable(Screen.MyTournaments) {
            MyTournamentsScreen(
                myTournaments = listOf(
                    Tournament("Carrom King", 20, 500)
                )
            )
        }

        // Task
        composable(Screen.Task) {
            TaskScreen(
                tasks = listOf(
                    Task("Watch Ad", false),
                    Task("Refer Friend", false),
                    Task("Daily Login", false)
                ),
                onTaskComplete = {}
            )
        }
    }
}
