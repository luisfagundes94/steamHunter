package com.luisfagundes.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY = "key"

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val urlWithApiKey = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .build()

        val requestWithApiKey = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(requestWithApiKey)
    }
}