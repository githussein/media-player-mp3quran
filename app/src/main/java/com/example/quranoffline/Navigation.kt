package com.example.quranoffline

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object AllReciter

@Serializable
data class Reciter(val reciterId: String)

@Serializable
object RadioStations

@Serializable
object Chapters