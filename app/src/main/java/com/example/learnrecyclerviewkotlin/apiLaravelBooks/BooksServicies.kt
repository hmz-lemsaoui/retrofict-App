package com.example.learnrecyclerviewkotlin.apiLaravelBooks

import com.example.learnrecyclerviewkotlin.Book
import com.example.learnrecyclerviewkotlin.dataBook
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

interface BooksServicies {
    @GET("books/")
    @SerializedName("data") fun getBooks() : Call<dataBook>
}