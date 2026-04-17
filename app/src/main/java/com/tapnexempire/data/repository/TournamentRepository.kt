package com.tapnexempire.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.TransactionModel
import com.tapnexempire.data.model.TransactionType
import com.tapnexempire.data.model.WalletModel 
import com.tapnexempire.data.model.TournamentModel
import javax.inject.Inject

class TournamentRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val tournamentRef = firestore.collection("tournaments")
    private val walletRef = firestore.collection("wallets")

    // 👀 Listen tournaments
fun listenToTournaments(onChange: (List<TournamentModel>) -> Unit) {
    tournamentRef.addSnapshotListener { snapshot, _ ->

        val list: List<TournamentModel> =
            snapshot?.documents?.mapNotNull { doc ->
                doc.toObject(TournamentModel::class.java)?.copy(id = doc.id)
            } ?: emptyList()

        onChange(list)
    }
}
}
