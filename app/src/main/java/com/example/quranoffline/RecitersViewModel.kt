package com.example.quranoffline

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReciterViewModel @Inject constructor(
    private val repository: RetrofitInstance
) : ViewModel() {
    private val _reciters = mutableStateOf<List<Reciter>>(emptyList())
    val reciters: State<List<Reciter>> = _reciters

    private val _selectedReciter = mutableStateOf<Reciter?>(null)
    val selectedReciter: State<Reciter?> = _selectedReciter

    private val _surahList = mutableStateOf<List<Surah>>(emptyList())
    val surahList: State<List<Surah>> = _surahList

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        fetchReciters()
    }

    private fun fetchReciters() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.api.getAllReciters()
                _reciters.value = response.reciters
                _isLoading.value = false
            } catch (e: Exception) {
                // Handle the error
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchReciterById(id: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getReciterById(reciterId = id)
                _selectedReciter.value = response.reciters.firstOrNull()
                _isLoading.value = false
            } catch (e: Exception) {
                // Handle the error
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchSurahList() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getSurahName()
                _surahList.value = response.suwar
                _isLoading.value = false
            } catch (e: Exception) {
                Log.e("ReciterViewModel", "Error fetching surahs: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}