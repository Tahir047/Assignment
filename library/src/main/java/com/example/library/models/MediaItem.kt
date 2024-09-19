package com.example.library.models

data class MediaItem(
    val id: String?,
    val title: String?, // For movies
    val name: String?,  // For TV shows
    val mediaType: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val popularity: Double?,
    val posterPath: String?,
    val backdropPath: String?,
    val originalLanguage: String?,
    val overview: String?,
    val releaseDate: String?,  // For movies
    val firstAirDate: String?, // For TV shows
    val genreIds: List<Int>?,
    val adult: Boolean? = false // Optional, applies to movies
)
