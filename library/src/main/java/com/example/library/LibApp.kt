package com.example.library

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class LibApp:Application() {
    companion object{
        val authToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NDMxMzZiMDRiMTNkZjQ5MGUyZmFlMGM2ZjA5NjBkZiIsIm5iZiI6MTcyNjc1Mzc3NS43OTc5MDYsInN1YiI6IjY2ZWMyYTM1NTE2OGE4OTZlMTIwMTQwNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.OCaho3Z0Z2uRlCigGxAFF2mvLzYjkdBjQvmrcUVci6I" // Replace with your actual Bearer token

        val baseUrl = "https://api.themoviedb.org/3/"
        val apiKey = "743136b04b13df490e2fae0c6f0960df" // Replace with your actual API key

    }
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@LibApp)
            modules(listOf(AppModules().getModeuls(this@LibApp)))
        }


    }
}