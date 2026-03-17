data class TournamentModel(
    val id: String = "",
    val name: String = "",
    val gameType: String = "",   // ludo, fight, quiz
    val entryFee: Long = 0,
    val prizePool: Long = 0,
    val maxPlayers: Int = 0,
    val joinedPlayers: Int = 0,
    val imageUrl: String = "",   // UI ke liye
    val status: String = "open", // open, full, completed
    val createdAt: Long = 0
)
