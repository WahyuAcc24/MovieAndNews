package com.wr15.mytestproject.acticity

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
import com.wr15.mytestproject.model.MReview
import com.wr15.mytestproject.server.HttpsTrustManager
import com.wr15.mytestproject.server.ServerMovieHost
import kotlinx.android.synthetic.main.item_bs_review.view.*
import kotlinx.android.synthetic.main.lay_bs_review.view.*
import kotlinx.android.synthetic.main.lay_detail_film.*
import org.json.JSONException


class DetailMovieActivity : AppCompatActivity() {

    lateinit var img_film: ImageView
    lateinit var img_rating: ImageView
    lateinit var img_back: ImageView

    lateinit var txt_sinopsis: TextView
    lateinit var txt_produser: TextView
    lateinit var txt_judul_film: TextView
    lateinit var txt_total_rating: TextView

    lateinit var btn_trailer: Button
    lateinit var rating: RatingBar
    lateinit var rv_genre: RecyclerView
    lateinit var rv_rating: RecyclerView
    lateinit var pb_review : ProgressBar


    private var requesQueue: RequestQueue? = null

    private var genreAdapter: GenreAdapter? = null
    private var reviewAdapter: ReviewAdapter? = null

    var bottomSheetDialog: BottomSheetDialog? = null
    private lateinit var pglistreview: ProgressBar


    var id_url = 0

    var model_genre: List<MGenre> = ArrayList()
    var model_review: List<MReview> = ArrayList()


    private var Rating: Double? = null

    private var context : Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_detail_film)

        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )

        HttpsTrustManager.allowAllSSL()

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        img_rating = findViewById(R.id.imgrating)
        img_back = findViewById(R.id.imgBackDetail)
        img_film = findViewById(R.id.imgMoviewDetail)

        txt_sinopsis = findViewById(R.id.txtSinopsis)
        txt_produser = findViewById(R.id.txtProduserDetail)
        txt_judul_film = findViewById(R.id.txtJudulFilmDetail)
        txt_total_rating = findViewById(R.id.txtTotalRatingDetail)

        btn_trailer = findViewById(R.id.btnTrailer)
        rating = findViewById(R.id.rtFilmDetail)


        rv_genre = findViewById(R.id.rvListGenre)


        requesQueue = Volley.newRequestQueue(applicationContext)

        model_genre = ArrayList()
        model_review = ArrayList()

        imgBackDetail.setOnClickListener {
            finish()
        }

        bottomSheetDialog = BottomSheetDialog(this@DetailMovieActivity)


        var view = layoutInflater.inflate(R.layout.lay_bs_review, null)

        bottomSheetDialog?.setContentView(view)

        rv_rating = view.findViewById(R.id.rvReviewUser)
        pb_review = view.findViewById(R.id.pbReviewUser)


        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        rv_rating.layoutManager = linearLayoutManager



        imgrating.setOnClickListener {

            showUserRating()

        }

        rv_genre.setLayoutManager(
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )


        dataDetailMovie()

        btn_trailer.setOnClickListener {

            showTrailer()

        }



    }

    fun showUserRating() {

        var data: MFilm = Gson().fromJson(
            intent.getStringExtra("data"),
            MFilm::class.java
        )

        id_url = data.id

        bottomSheetDialog?.show()

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            ServerMovieHost.DETAIL_FILM + id_url + "/reviews?" + ServerMovieHost.APIKEY + ServerMovieHost.BAHASA,
            null,
            { response ->
                Log.e("TAG_REVIEW", response.toString())
                try {
                    pb_review.setVisibility(View.GONE)

                    val jsonArray = response.getJSONArray("results")

                    for (i in 0 until jsonArray.length()) {

                        val jsonObject = jsonArray.getJSONObject(i)
                        val username =
                            jsonObject.getJSONObject("author_details").getString("username")

                        val komen = jsonObject.getString("content")

                        var data_review = MReview()

                        data_review.setUsername(username)
                        data_review.setContent(komen)

                        (model_review as ArrayList<MReview>).add(data_review)

                        reviewAdapter = ReviewAdapter(applicationContext, model_review)

                        rv_rating.adapter = reviewAdapter


                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) // pengecek salah dengan Volley library
        { error ->
            pglistreview.setVisibility(View.GONE)
            Log.e("TAG_GENRE", "Review Error :" + error.message)
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        }
        requesQueue?.add(jsonObjectRequest)


    }

    fun dataDetailMovie(){


        var data: MFilm = Gson().fromJson(
            intent.getStringExtra("data"),
            MFilm::class.java
        )


        id_url = data.id

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            ServerMovieHost.DETAIL_FILM + id_url + "?" + ServerMovieHost.APIKEY,
            null,
            { response ->
                Log.e("TAG_GENRE", response.toString())
                try {

                    val jsonArray = response.getJSONArray("genres")

                    for (i in 0 until jsonArray.length()) {


                        val jsonObject = jsonArray.getJSONObject(i)

                        var data_genre = MGenre()


                        data_genre.setName(jsonObject.getString("name"))


                        (model_genre as ArrayList<MGenre>).add(data_genre)

                        genreAdapter = GenreAdapter(applicationContext, model_genre)

                        rv_genre.adapter = genreAdapter

                        val layoutManager = FlexboxLayoutManager(applicationContext).apply {
                            justifyContent = JustifyContent.CENTER
                            alignItems = AlignItems.CENTER
                            flexDirection = FlexDirection.ROW
                            flexWrap = FlexWrap.WRAP
                        }
                        rv_genre.layoutManager = layoutManager

                    }

                    val jsonArrayProduction = response.getJSONArray("production_companies")

                    for (i in 0 until jsonArrayProduction.length()) {
                        if (i == 0) {

                            val jsonObjectProduction = jsonArrayProduction.getJSONObject(i)
                            txt_produser.setText(jsonObjectProduction.getString("name"))

                        }


                    }


                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) // pengecek salah dengan Volley library
        { error ->
            Log.e("TAG_GENRE", "Login Error :" + error.message)
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        }
        requesQueue?.add(jsonObjectRequest)


        txt_judul_film.setText(data.title)
        txt_sinopsis.setText(data.overview)
        txt_total_rating.setText(data.vote_average.toString())


        Glide.with(this)
            .load(ServerMovieHost.URLIMAGE + data.poster_path)
            .error(R.drawable.ic_baseline_movie_24)
            .placeholder(R.drawable.ic_baseline_movie_24)
            .into(img_film)



        Rating = data.vote_average

        val newValue = Rating
        rating.setNumStars(5)
        rating.setStepSize(0.5f)
        if (newValue != null) {
            rating.setRating((newValue / 2).toFloat())
        }

    }


    fun showTrailer(){


        var data: MFilm = Gson().fromJson(
            intent.getStringExtra("data"),
            MFilm::class.java
        )


        id_url = data.id



        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            ServerMovieHost.BASEURL + ServerMovieHost.MOVIE_VIDEO + id_url + "/videos?" + ServerMovieHost.APIKEY,
            null,
            { response ->
                Log.e("TAG_GENRE", response.toString())
                try {

                    val jsonArray = response.getJSONArray("results")

                    for (i in 0 until jsonArray.length()) {

                        val jsonObject = jsonArray.getJSONObject(i)

                        var type = jsonObject.getString("type")

                        if (type == "Trailer") {

                            var key = jsonObject.getString("key")


                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.setData(Uri.parse("https://www.youtube.com/watch?v=" + key))
                            applicationContext.startActivity(intent)


                        }


                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) // pengecek salah dengan Volley library
        { error ->
            Log.e("TAG_GENRE", "Login Error :" + error.message)
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        }
        requesQueue?.add(jsonObjectRequest)



    }
    fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val window: Window = activity.window
        val winParams: WindowManager.LayoutParams = window.getAttributes()
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        window.setAttributes(winParams)
    }



}