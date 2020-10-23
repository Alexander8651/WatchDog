package com.andromoticaia.watchingdog.vo

import com.andromoticaia.watchingdog.domain.WebService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val ROOT = "https://dog.ceo/api/breeds/image/"

val interceptor:HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
}

val cliente:OkHttpClient = OkHttpClient.Builder().apply {
    this.addInterceptor(interceptor)
}.build()

object RetrofitClient {

    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(ROOT)
            .client(cliente)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(WebService::class.java)
    }

}