package com.example.quranoffline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MediaPlayerViewModelFactory(private val mediaPlayerService: MediaPlayerService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReciterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReciterViewModel(mediaPlayerService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}