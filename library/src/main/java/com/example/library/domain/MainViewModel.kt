package com.example.library.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library.data.MediaRepository
import com.example.library.models.MediaItem
import com.example.library.models.MediaResponse
import kotlinx.coroutines.launch


class MainViewModel( val repository: MediaRepository) : ViewModel() {


    private val _mediaItems = MutableLiveData<List<MediaItem>>()
    val mediaItems: LiveData<List<MediaItem>> get() = _mediaItems

    private val _mediaItemsSearch = MutableLiveData<List<MediaItem>>()
    val mediaItemsSearch: LiveData<List<MediaItem>> get() = _mediaItemsSearch

    fun discoverMovies(mediaType: String = "movie") {
        viewModelScope.launch {
            try {
                println("discoverMoviesCheckMediaType : "+mediaType)
                val response = repository.apiService.discoverMovies(mediaType = mediaType)
                println("discoverMoviesCheckMediaType :response ")



                val mediaItems = mapResponseToMediaItems(response.results)
                _mediaItems.postValue(mediaItems)
                // Handle the response here (e.g., update the UI)
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle any errors (e.g., network errors)
            }
        }

    }
    fun fetchMediaItems(query: String="jack") {
        viewModelScope.launch {
            try {
                val response = repository.apiService.searchMulti(
                    includeAdult = false,
                    language = "en-US",
                    page = 1,
                    query = query
                )

//                val response = repository.apiService.searchMulti(
//                    query = "Jack",
//                    includeAdult = false, // or true if you want to include adult content
//                    language = "en-US",
//                    page = 1,
////                    apiKey = apiKey,
////                    authToken = authToken,
//
//                )
                val mediaItems = mapResponseToMediaItems(response.results)
                _mediaItemsSearch.postValue(mediaItems)
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle the error
            }
        }
    }

    fun mapResponseToMediaItems(response: List<MediaResponse>): List<MediaItem> {
        return response.map { mediaResponse ->
            MediaItem(
                id = mediaResponse.id.toString(),
                title = mediaResponse.title,
                name = mediaResponse.name,
                mediaType = mediaResponse.media_type,
                voteAverage = mediaResponse.vote_average,
                voteCount = mediaResponse.vote_count,
                popularity = mediaResponse.popularity,
                posterPath = mediaResponse.poster_path,
                backdropPath = mediaResponse.backdrop_path,
                originalLanguage = mediaResponse.original_language,
                overview = mediaResponse.overview,
                releaseDate = mediaResponse.release_date,
                firstAirDate = mediaResponse.first_air_date,
                genreIds = mediaResponse.genre_ids,
                adult = mediaResponse.adult == true
            )
        }
    }

}
