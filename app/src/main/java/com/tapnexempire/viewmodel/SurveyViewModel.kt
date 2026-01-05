package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.SurveyRepository
import kotlinx.coroutines.launch

class SurveyViewModel(
    private val repository: SurveyRepository
) : ViewModel() {

    fun onPaidTournamentJoined(userId: String) {
        viewModelScope.launch {
            repository.increaseSurveyCount(userId)
        }
    }
}
