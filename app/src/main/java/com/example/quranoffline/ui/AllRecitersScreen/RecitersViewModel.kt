package com.example.quranoffline.ui.AllRecitersScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranoffline.data.Reciter
import com.example.quranoffline.data.ReciterResponse
import com.example.quranoffline.data.RecitersApi
import com.example.quranoffline.data.Surah
import com.example.quranoffline.data.SurahUi
import com.example.quranoffline.ui.formatServerUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReciterViewModel @Inject constructor(
    private val repository: RecitersRepository
) : ViewModel() {

    private val _resultState = MutableStateFlow<ReciterResultState>(ReciterResultState.Idle)
    val resultState = _resultState.asStateFlow()

    private val _selectedReciter = mutableStateOf<Reciter?>(null)
    val selectedReciter: State<Reciter?> = _selectedReciter

    private val _surahList = MutableStateFlow<List<SurahUi>>(emptyList())
    val surahList = _surahList.asStateFlow()


    init {
        fetchReciters()
    }

    private fun fetchReciters() {
        viewModelScope.launch {
            _resultState.emit(ReciterResultState.Loading)

            try {
                val response = repository.getAllReciters()
                _resultState.emit(ReciterResultState.Success(response))
            } catch (e: Exception) {
                _resultState.emit(ReciterResultState.Failure(e))
            }
        }
    }

    fun fetchReciterById(id: String) {
        viewModelScope.launch {
            _resultState.emit(ReciterResultState.Loading)
            try {
                val response = repository.getReciterById(reciterId = id)

                _selectedReciter.value = response.reciters.firstOrNull()
                _resultState.emit(ReciterResultState.Success(response))

                val availableSurahId = response.reciters.firstOrNull()?.moshaf?.firstOrNull()?.surah_list?.split(",")?.map { it.toInt() }?.toList()

                val surahList = fetchSurahList()

                val surahNameList = availableSurahId?.mapNotNull { surahId ->
                    val surah = surahList.firstOrNull { it.id == surahId }
                    SurahUi(surah = surah, server = response.reciters.firstOrNull()?.moshaf?.firstOrNull()?.server?.formatServerUrl(surahId))
                }
                _surahList.emit(surahNameList.orEmpty())

            } catch (e: Exception) {
                _resultState.emit(ReciterResultState.Failure(e))
            }
        }
    }

    suspend fun fetchSurahList(): List<Surah> {
        return try {
            val response = RecitersApi.api.getSurahName()
            response.suwar
        } catch (e: Exception) {
            Log.e("ReciterViewModel", "Error fetching surahs: ${e.message}")
            emptyList()
        }
    }

}

sealed interface ReciterResultState {
    data object Idle : ReciterResultState
    data object Loading : ReciterResultState
    data class Success(val response: ReciterResponse) : ReciterResultState
    data class Failure(val e: Exception) : ReciterResultState
}