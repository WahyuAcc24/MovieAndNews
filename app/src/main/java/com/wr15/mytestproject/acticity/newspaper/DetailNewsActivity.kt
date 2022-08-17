package com.wr15.mytestproject.acticity.newspaper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.android.flexbox.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.wr15.mytestproject.R
import com.wr15.mytestproject.adapter.GenreAdapter
import com.wr15.mytestproject.adapter.ReviewAdapter
import com.wr15.mytestproject.model.MFilm
import com.wr15.mytestproject.model.MGenre
import com.wr15.mytestproject.model.MNews
import com.wr15.mytestproject.model.MReview
import com.wr15.mytestproject.server.HttpsTrustManager
import com.wr15.mytestproject.server.ServerMovieHost
import kotlinx.android.synthetic.main.item_bs_review.view.*
import kotlinx.android.synthetic.main.lay_bs_review.view.*
import kotlinx.android.synthetic.main.lay_detail_film.*
import org.json.JSONException


class DetailNewsActivity : AppCompatActivity() {

    lateinit var img_berita: ImageView
    lateinit var img_back: ImageView

    lateinit var txt_penulis: TextView
    lateinit var txt_tgl: TextView
    lateinit var txt_judul_berita: TextView


    lateinit var btn_news: Button



    private var requesQueue: RequestQueue? = null

    private var context : Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_detail_news)


        HttpsTrustManager.allowAllSSL()

        img_berita = findViewById(R.id.imgNewsDetail)
        img_back = findViewById(R.id.imgBackDetailNews)

        txt_judul_berita = findViewById(R.id.txtJudulNewsDetail)
        txt_penulis = findViewById(R.id.txtPenulisBerita)
        txt_tgl = findViewById(R.id.txtTanggal)

        btn_news = findViewById(R.id.btnNews)


        requesQueue = Volley.newRequestQueue(applicationContext)

        img_back.setOnClickListener {
            finish()
        }



        var data: MNews = Gson().fromJson(
            intent.getStringExtra("datanews"),
            MNews::class.java
        )

        txt_judul_berita.setText(data.title)
        txt_penulis.setText(data.author)
        txt_tgl.setText(data.publishedAt)

        Glide.with(this)
            .load(data.urlToImage)
            .error(R.drawable.ic_baseline_movie_24)
            .placeholder(R.drawable.ic_baseline_movie_24)
            .into(img_berita)


        btn_news.setOnClickListener {

            val intent = Intent(this,WebNewsActivity::class.java)
            intent.putExtra("urlnews",data.url)
            startActivity(intent)

        }



    }

}