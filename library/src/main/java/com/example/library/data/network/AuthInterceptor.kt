package com.example.library.data.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $authToken") // Add Bearer token to the header
            .header("accept", "application/json") // Add accept header


        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}