package com.example.library.data

import com.example.library.models.PaginatedMediaResponse
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiService {


    @GET("discover/movie") // Can be used for both movie and tv, just change base URL accordingly
    suspend fun discoverMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("media_type") mediaType: String // Add media_type as a query param
    ): PaginatedMediaResponse

    @GET("search/multi")
    suspend fun searchMulti(
        @Query("include_adult") includeAdult: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("query") query: String // Pass query as needed
    ): PaginatedMediaResponse


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): PaginatedMediaResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): PaginatedMediaResponse


}



