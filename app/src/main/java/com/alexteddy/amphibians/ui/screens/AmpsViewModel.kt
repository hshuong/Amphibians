package com.alexteddy.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alexteddy.amphibians.AmpsApplication
import com.alexteddy.amphibians.data.AmpsRepository
import com.alexteddy.amphibians.network.Amphibian
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmpsUiState {
    data class Success(val amps: List<Amphibian>) : AmpsUiState
    object Error : AmpsUiState
    object Loading : AmpsUiState
}

class AmpsViewModel(private val ampsRepository: AmpsRepository) : ViewModel() {
    var ampsUiState: AmpsUiState by mutableStateOf(AmpsUiState.Loading)
        private set
    init {
        getAmphibiansInViewModel()
    }

    fun getAmphibiansInViewModel() {
        viewModelScope.launch {
            ampsUiState = try {
                AmpsUiState.Success(
                    ampsRepository.getAmphibians()
                )
            } catch (e: IOException) {
                AmpsUiState.Error
            }
        }
    }
    // cho nay cho companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmpsApplication)
                val ampsRepository = application.container.ampsRepository
                AmpsViewModel(ampsRepository = ampsRepository)
            }
        }
    }
}

