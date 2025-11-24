package com.example.workmate_test.di

import org.koin.dsl.module
import com.example.workmate_test.data.api.UserApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<UserApiService> {
        get<Retrofit>().create(UserApiService::class.java)
    }

}