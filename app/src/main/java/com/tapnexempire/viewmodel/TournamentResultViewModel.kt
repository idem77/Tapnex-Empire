package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.repository.TournamentResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TournamentResultViewModel @Inject constructor(
    private val repo: TournamentResultRepository
) : ViewModel() {

    fun submitResult() {
        // Future logic
    }
}
