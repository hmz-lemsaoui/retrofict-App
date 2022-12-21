package com.example.learnrecyclerviewkotlin.apiLaravelBooks

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "https://fastidious-commitme.000webhostapp.com/api/"
    val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    var client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    // by lazy : permet de dire : en faire une instance de retrofit seulemnet lors de l'apilation
    private val retofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun buildBooksService(): BooksServicies = retofit.create(BooksServicies::class.java)
}