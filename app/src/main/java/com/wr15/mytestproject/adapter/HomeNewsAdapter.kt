package com.wr15.mytestproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.bumptech.glide.Glide
import com.wr15.mytestproject.R
import com.wr15.mytestproject.model.MNews

class HomeNewsAdapter(private val mcontext: Context, private val mNewss: List<MNews>) :
    RecyclerView.Adapter<HomeNewsAdapter.Holder>() {
    private var listenermNews: ItemClickListener<MNews>? = null
    private val TAG = HomeNewsAdapter::class.java.simpleName
    private val requestQueue: RequestQueue? = null
    private val Rating = 0.0
    fun setListener(listenerMNews: ItemClickListener<*>?) {
        listenermNews = listenerMNews as ItemClickListener<MNews>?
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data_News = mNewss[position]
        Glide.with(holder.foto.context)
            .load(data_News.urlToImage)
            .error(R.drawable.ic_baseline_movie_24)
            .placeholder(R.drawable.ic_baseline_movie_24)
            .into(holder.foto)
        holder.nama.text = data_News.title
        holder.item.setOnClickListener(View.OnClickListener { v ->
            listenermNews!!.onClicked(
                mNewss[position], position, v
            )
        })
    }

    override fun getItemCount(): Int {
        return mNewss.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView
        val foto: ImageView
        val item: LinearLayout

        init {
            item = itemView.findViewById(R.id.linearNews)
            nama = itemView.findViewById(R.id.txtJudulNews)
            foto = itemView.findViewById<View>(R.id.imgNews) as ImageView
        }
    }
}