package com.example.library.models

data class MediaResponse(
    val id: Int,
    val vote_average: Double,
    val vote_count: Int,
    val media_type: String,
    val title: String?,       // For movies
    val name: String?,        // For TV shows
    val popularity: Double,
    val poster_path: String?,
    val original_language: String,
    val genre_ids: List<Int>,
    val backdrop_path: String?,
    val adult: Boolean?,
    val overview: String,
    val release_date: String?,  // For movies
    val first_air_date: String? // For TV shows
)

data class PaginatedMediaResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MediaResponse>
)
