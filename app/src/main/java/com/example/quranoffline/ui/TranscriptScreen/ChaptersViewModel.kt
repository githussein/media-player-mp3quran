package com.example.quranoffline.ui.TranscriptScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranoffline.data.QSurahResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChaptersViewModel @Inject constructor(
    private val repository: ChaptersRepository
) : ViewModel() {
    private val _resultState = MutableStateFlow<ChaptersResultState>(ChaptersResultState.Idle)
    val resultState = _resultState.asStateFlow()

    init {
        fetchChapters()
    }

    private fun fetchChapters() {
        viewModelScope.launch {
            _resultState.emit(ChaptersResultState.Loading)

            try {
                val response = repository.getChapters()
                println(response)
                _resultState.emit(ChaptersResultState.Success(response))
            } catch (e: Exception) {
                _resultState.emit(ChaptersResultState.Failure(e))
                println(e)
            }
        }
    }
}

sealed interface ChaptersResultState {
    data object Idle : ChaptersResultState
    data object Loading : ChaptersResultState
    data class Success(val response: QSurahResponse) : ChaptersResultState
    data class Failure(val e: Exception) : ChaptersResultState
}