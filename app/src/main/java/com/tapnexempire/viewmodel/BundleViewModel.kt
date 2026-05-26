package com.tapnexempire.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.model.BundleModel
import com.tapnexempire.data.repository.BundleRepository
import kotlinx.coroutines.launch

class BundleViewModel : ViewModel() {

    private val repository =
        BundleRepository()

    var bundles =
        mutableStateOf<List<BundleModel>>(
            emptyList()
        )

        private set

    init {

        loadBundles()
    }

    private fun loadBundles() {

        viewModelScope.launch {

            bundles.value =
                repository.getBundles()
        }
    }
}
