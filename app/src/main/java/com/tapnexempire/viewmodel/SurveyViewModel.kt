package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.repository.SurveyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val repository: SurveyRepository
) : ViewModel() {

    fun onPaidTournamentJoined(userId: String) {
        viewModelScope.launch {
            repository.increaseSurveyCount(userId)
        }
    }
}
