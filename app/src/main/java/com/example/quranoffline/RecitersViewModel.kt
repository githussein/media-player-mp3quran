package com.example.quranoffline

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ReciterViewModel : ViewModel() {
    private val _reciters = mutableStateOf<List<Reciter>>(emptyList())
    val reciters: State<List<Reciter>> = _reciters

    init {
        fetchReciters()
    }

    private fun fetchReciters() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getReciters()
                _reciters.value = response.reciters
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }
}