package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.model.MyTournamentModel
import com.tapnexempire.data.repository.MyTournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyTournamentViewModel @Inject constructor(
    private val repo: MyTournamentRepository
) : ViewModel() {

    fun getMyTournaments(

        userId: String,

        onResult: (List<MyTournamentModel>) -> Unit
    ) {

        repo.getMyTournaments(
            userId,
            onResult
        )
    }
}
