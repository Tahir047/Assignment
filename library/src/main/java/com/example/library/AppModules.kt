package com.example.library

import com.example.library.LibApp.Companion.authToken
import com.example.library.LibApp.Companion.baseUrl
import com.example.library.data.ApiService
import com.example.library.data.MediaRepository
import com.example.library.data.network.AuthInterceptor
import com.example.library.domain.MainViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppModules {

    fun getModeuls(appClass: LibApp): Module {
        return module {
            single { retrofit.create(ApiService::class.java) } // Providing ApiService
            single { MediaRepository(get()) } // Providing MediaRepository with ApiService
            viewModel { MainViewModel(get()) } // Providing MainViewModel with MediaRepository

        }


    }




    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(authToken))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient) // Set OkHttpClient with interceptor
        .addConverterFactory(GsonConverterFactory.create()) // or your preferred converter
        .build()



}