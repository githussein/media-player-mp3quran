package com.example.quranoffline.ui.RadioStationsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranoffline.data.RadioStationsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadioStationsViewModel @Inject constructor(
    private val repository: RadioStationsRepository
) : ViewModel() {
    private val _resultState = MutableStateFlow<RadiosResultState>(RadiosResultState.Idle)
    val resultState = _resultState.asStateFlow()

    init {
        fetchRadioStations()
    }

    private fun fetchRadioStations() {
        viewModelScope.launch {
            _resultState.emit(RadiosResultState.Loading)

            try {
                val response = repository.getRadioStations()
                _resultState.emit(RadiosResultState.Success(response))
            } catch (e: Exception) {
                _resultState.emit(RadiosResultState.Failure(e))
            }
        }
    }
}


sealed interface RadiosResultState {
    data object Idle : RadiosResultState
    data object Loading : RadiosResultState
    data class Success(val response: RadioStationsResponse) : RadiosResultState
    data class Failure(val e: Exception) : RadiosResultState
}