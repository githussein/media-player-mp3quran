package com.example.quranoffline.ui.HadithScript

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranoffline.data.BookResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {
    private val _resultState = MutableStateFlow<BookResultState>(BookResultState.Idle)
    val resultState = _resultState.asStateFlow()

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            _resultState.emit(BookResultState.Loading)

            try {
                val response = repository.getBooks()
                _resultState.emit(BookResultState.Success(response))
                println(response)
            } catch (e: Exception) {
                _resultState.emit(BookResultState.Failure(e))
                Log.e("BookViewModel", "Error fetching books: ${e.message}")
            }
        }
    }
}


sealed interface BookResultState {
    data object Idle : BookResultState
    data object Loading : BookResultState
    data class Success(val response: BookResponse) : BookResultState
    data class Failure(val e: Exception) : BookResultState
}