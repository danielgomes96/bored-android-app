package com.devlabs.data.di

import com.devlabs.core.Consts.Companion.BASE_API_URL
import com.devlabs.data.service.BoredAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory {
        getBoredAPIService(
            get<Retrofit>()
        )
    }

    single {
        createBoredAPIService(
            get()
        )
    }

    factory {
        createOkHttpClient()
    }
}

private fun getBoredAPIService(retrofit: Retrofit): BoredAPI =
    retrofit.create(BoredAPI::class.java)

private fun createBoredAPIService(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_API_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun createOkHttpClient(): OkHttpClient {
    val timeoutInSeconds = 10L
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .readTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}