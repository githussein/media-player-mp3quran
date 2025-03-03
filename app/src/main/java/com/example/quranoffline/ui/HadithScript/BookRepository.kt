package com.example.quranoffline.ui.HadithScript

import com.example.quranoffline.data.BookChaptersResponse
import com.example.quranoffline.data.BookResponse
import com.example.quranoffline.data.BookService
import javax.inject.Inject

interface IBooksRepository {
    suspend fun getBooks(): BookResponse
    suspend fun getBookById(bookSlug: String): BookChaptersResponse
}

class BookRepository @Inject constructor(
    private val apiService: BookService
) : IBooksRepository {

    override suspend fun getBooks(): BookResponse {
        return apiService.api.getBooks()
    }

    override suspend fun getBookById(bookSlug: String): BookChaptersResponse {
        return apiService.api.getBookById(bookSlug)
    }
}