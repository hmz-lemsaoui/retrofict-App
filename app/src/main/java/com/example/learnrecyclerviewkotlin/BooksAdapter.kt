package com.example.learnrecyclerviewkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BooksAdapter(
    var books: List<Book>
) : RecyclerView.Adapter<BooksAdapter.BookHolder>(), Filterable {
    var listBooksFiltrer: List<Book> = ArrayList()

    init {
        listBooksFiltrer = books
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var strSearch = p0.toString()
                listBooksFiltrer = if (strSearch.isEmpty()) {
                    books
                } else {
                    var resultsBooks = ArrayList<Book>()
                    for (book in books) {
                        if (book.titre.lowercase().contains(strSearch.lowercase())
                            || book.nom.lowercase().contains(strSearch.lowercase())
                        ) {
                            resultsBooks.add(book)
                        }
                    }
                    resultsBooks
                }
                var filterResults = FilterResults()
                filterResults.values = listBooksFiltrer
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                listBooksFiltrer = p1?.values as List<Book>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_book, parent, false)
        return BookHolder(view)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val book = listBooksFiltrer[position]
        holder.apply {
            titrePost.text = book.titre
            ratingpost.rating = book.rating
            desciptionpost.text = book.description
            nomAutheur.text = book.nom
            // en fait appel au piccaso
            Picasso.get().load(book.image).into(imagepost)
        }
    }

    override fun getItemCount(): Int {
        return listBooksFiltrer.size
    }

    class BookHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var imagepost: ImageView
        var titrePost: TextView
        var nomAutheur: TextView
        var desciptionpost: TextView
        var ratingpost: RatingBar

        init {
            imagepost = itemView.findViewById(R.id.imagepost)
            titrePost = itemView.findViewById(R.id.titrePost)
            nomAutheur = itemView.findViewById(R.id.nomAutheur)
            desciptionpost = itemView.findViewById(R.id.descriptionPost)
            ratingpost = itemView.findViewById(R.id.ratingPost)
        }
    }


}