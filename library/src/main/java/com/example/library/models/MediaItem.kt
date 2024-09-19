package com.example.library.models

import java.io.Serializable


import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createIntArray()?.toList(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(name)
        parcel.writeString(mediaType)
        parcel.writeValue(voteAverage)
        parcel.writeValue(voteCount)
        parcel.writeValue(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(backdropPath)
        parcel.writeString(originalLanguage)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeString(firstAirDate)
        parcel.writeIntArray(genreIds?.toIntArray())
        parcel.writeValue(adult)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MediaItem> {
        override fun createFromParcel(parcel: Parcel): MediaItem {
            return MediaItem(parcel)
        }

        override fun newArray(size: Int): Array<MediaItem?> {
            return arrayOfNulls(size)
        }
    }
}
