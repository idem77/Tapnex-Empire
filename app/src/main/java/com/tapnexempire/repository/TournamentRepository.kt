package com.tapnexempire.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.models.TournamentModel
import kotlinx.coroutines.tasks.await

class TournamentRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    private val userId: String
        get() = auth.currentUser?.uid ?: throw Exception("User not logged in")

    private val tournamentsRef = firestore.collection("tournaments")
    private val usersRef = firestore.collection("users")

    // Get all active tournaments
    suspend fun getTournaments(): List<TournamentModel> {
        val snapshot = tournamentsRef.get().await()
        return snapshot.documents.map { it.toObject(TournamentModel::class.java)!! }
    }

    // Get tournaments joined by current user
    suspend fun getMyTournaments(): List<TournamentModel> {
        val snapshot = tournamentsRef
            .whereArrayContains("participants", userId)
            .get()
            .await()
        return snapshot.documents.map { it.toObject(TournamentModel::class.java)!! }
    }

    // Join tournament
    suspend fun joinTournament(tournamentId: String, entryFee: Int) {
        val userDoc = usersRef.document(userId)
        val userCoins = userDoc.get().await().getLong("coins")?.toInt() ?: 0

        if (entryFee > userCoins) throw Exception("Not enough coins to join")

        // Deduct coins
        userDoc.update("coins", userCoins - entryFee).await()

        // Add user to tournament participants
        tournamentsRef.document(tournamentId)
            .update("participants", FieldValue.arrayUnion(userId))
            .await()
    }

    // Distribute prize (internal use, admin action)
    suspend fun distributePrize(tournamentId: String, prizeMap: Map<String, Int>) {
        val tournamentDoc = tournamentsRef.document(tournamentId)
        prizeMap.forEach { (uid, coins) ->
            val userDoc = usersRef.document(uid)
            val currentCoins = userDoc.get().await().getLong("coins")?.toInt() ?: 0
            userDoc.update("coins", currentCoins + coins).await()
        }
        // Optionally mark tournament as completed
        tournamentDoc.update("status", "completed").await()
    }
}
