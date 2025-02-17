package com.example.quranoffline

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class ReciterViewModel(mediaPlayerService: MediaPlayerService) : ViewModel() {
    private val serviceRef = WeakReference(mediaPlayerService)

    private val _reciters = mutableStateOf<List<Reciter>>(emptyList())
    val reciters: State<List<Reciter>> = _reciters

    private val _selectedReciter = mutableStateOf<Reciter?>(null)
    val selectedReciter: State<Reciter?> = _selectedReciter

    private val _surahList = mutableStateOf<List<Surah>>(emptyList())
    val surahList: State<List<Surah>> = _surahList

    val isPlaying: StateFlow<Boolean>?
        get() = serviceRef.get()?.isPlaying

    init {
        fetchReciters()
    }

    fun playStopAudio(url: String) {
        if (isPlaying?.value == true) {
            stopAudio()
        } else {
            serviceRef.get()?.play(url)
        }
    }

    private fun stopAudio() {
        serviceRef.get()?.stop()
    }

    private fun fetchReciters() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getAllReciters()
                _reciters.value = response.reciters
            } catch (e: Exception) {
                println(e)
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

    fun fetchSurahList() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getSurahName()
                _surahList.value = response.suwar
            } catch (e: Exception) {
                Log.e("ReciterViewModel", "Error fetching surahs: ${e.message}")
            }
        }
    }
}