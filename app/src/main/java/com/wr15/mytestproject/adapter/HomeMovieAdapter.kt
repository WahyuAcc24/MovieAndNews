package com.wr15.mytestproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.bumptech.glide.Glide
import com.wr15.mytestproject.R
import com.wr15.mytestproject.model.MFilm
import com.wr15.mytestproject.server.ServerMovieHost

class HomeMovieAdapter(private val mcontext: Context, private val mfilms: List<MFilm>) :
    RecyclerView.Adapter<HomeMovieAdapter.Holder>() {
    private var listenermfilm: ItemClickListener<MFilm>? = null
    private val TAG = HomeMovieAdapter::class.java.simpleName
    private val requestQueue: RequestQueue? = null
    private var Rating = 0.0
    fun setListener(listenerMfilm: ItemClickListener<*>?) {
        listenermfilm = listenerMfilm as ItemClickListener<MFilm>?
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data_film = mfilms[position]
        Glide.with(holder.foto.context)
            .load(ServerMovieHost.URLIMAGE + data_film.poster_path)
            .error(R.drawable.ic_baseline_movie_24)
            .placeholder(R.drawable.ic_baseline_movie_24)
            .into(holder.foto)
        holder.nama.text = data_film.title
        Rating = data_film.vote_average
        val newValue = Rating.toFloat()
        holder.ratingBar.numStars = 5
        holder.ratingBar.stepSize = 0.5.toFloat()
        holder.ratingBar.rating = newValue / 2
        holder.item.setOnClickListener(View.OnClickListener { v ->
            listenermfilm!!.onClicked(
                mfilms[position], position, v
            )
        })
    }

    override fun getItemCount(): Int {
        return mfilms.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView
        val foto: ImageView
        val item: LinearLayout
        val ratingBar: RatingBar

        init {
            item = itemView.findViewById(R.id.linearFilm)
            nama = itemView.findViewById(R.id.txtNamaFilm)
            foto = itemView.findViewById<View>(R.id.imgFilm) as ImageView
            ratingBar = itemView.findViewById<View>(R.id.rtFilm) as RatingBar
        }
    }
}