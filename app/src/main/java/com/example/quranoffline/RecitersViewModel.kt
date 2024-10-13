package com.example.quranoffline

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ReciterViewModel : ViewModel() {
    private val _reciters = mutableStateOf<List<Reciter>>(emptyList())
    val reciters: State<List<Reciter>> = _reciters

    private val _selectedReciter = mutableStateOf<Reciter?>(null)
    val selectedReciter: State<Reciter?> = _selectedReciter

    init {
        fetchReciters()
    }

    private fun fetchReciters() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getAllReciters()
                _reciters.value = response.reciters
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun fetchReciterById(id: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getReciterById(reciterId = id)
                _selectedReciter.value = response.reciters.firstOrNull()
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }
}