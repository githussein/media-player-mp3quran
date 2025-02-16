package com.example.quranoffline

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object AllReciter

@Serializable
data class ReciterNavigation(val reciterId: String)

@Serializable
object RadioStations