package com.example.learnrecyclerviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learnrecyclerviewkotlin.apiLaravelBooks.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recylerViewBooks : RecyclerView
    lateinit var adapterBooks : BooksAdapter
    lateinit var toolbar : Toolbar
    lateinit var charsearch : EditText
    lateinit var imgeSearch : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recylerViewBooks = findViewById(R.id.recylerViewBooks)
        toolbar = findViewById(R.id.toolbar)
        charsearch = findViewById(R.id.charsearch)
        imgeSearch = findViewById(R.id.imgeSearch)

        setSupportActionBar(toolbar)

        val booksService = RetrofitInstance.buildBooksService()
        booksService.getBooks().enqueue(object : Callback<dataBook>{
            override fun onResponse(call: Call<dataBook>, response: Response<dataBook>) {
                if (response.isSuccessful){
                    val books = response.body()?.ListBooks
                    books?.let {
                        adapterBooks = BooksAdapter(books)
                        recylerViewBooks.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            this.adapter = adapterBooks
                        }
                    }
                }
            }

            override fun onFailure(call: Call<dataBook>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_LONG).show()
                println(t.message)
            }


        })

        imgeSearch.setOnClickListener{
            val searchValue = charsearch.text.toString()
//            charsearch.setText(searchValue)
            adapterBooks.filter.filter(searchValue)
        }
    }
}