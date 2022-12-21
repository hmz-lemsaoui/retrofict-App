package com.example.learnrecyclerviewkotlin

import com.google.gson.annotations.SerializedName

data class dataBook(
    @SerializedName("data")
    var ListBooks : List<Book>
)
data class Book(
    var id : Int,
    var titre : String,
    var rating : Float,
    var nom: String,
    var image : String,
    var description : String
)

