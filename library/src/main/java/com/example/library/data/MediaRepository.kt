package com.example.library.data

import com.example.library.models.MediaItem

class MediaRepository( val apiService: ApiService) {

    var selectedMediaItem: MediaItem? = null
}