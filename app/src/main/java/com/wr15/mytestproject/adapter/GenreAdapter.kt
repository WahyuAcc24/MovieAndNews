package com.wr15.mytestproject.adapter

import android.content.Context
import com.wr15.mytestproject.model.MGenre
import androidx.recyclerview.widget.RecyclerView
import com.wr15.mytestproject.adapter.GenreAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.wr15.mytestproject.R
import android.widget.TextView

class GenreAdapter(private val mcontext: Context, private val mgenres: List<MGenre>) :
    RecyclerView.Adapter<GenreAdapter.Holder>() {

    private val TAG_GENRE = GenreAdapter::class.java.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.genre.text = mgenres[position].name
    }

    override fun getItemCount(): Int {
        return mgenres.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genre: TextView

        init {
            genre = itemView.findViewById(R.id.txtGenreDetail)
        }
    }
}