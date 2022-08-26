package com.wr15.mytestproject.acticity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.wr15.mytestproject.R
import com.wr15.mytestproject.acticity.newspaper.HomeNewsActivity
import com.wr15.mytestproject.adapter.HomeMovieAdapter
import com.wr15.mytestproject.adapter.ItemClickListener
import com.wr15.mytestproject.model.MFilm
import com.wr15.mytestproject.server.HttpsTrustManager
import com.wr15.mytestproject.server.ServerMovieHost
import kotlinx.android.synthetic.main.lay_home_movie.*
import org.json.JSONException
import org.json.JSONObject


class HomeMovieActivity : AppCompatActivity() {

    private lateinit var rv_film: RecyclerView
    private lateinit var pg_film: ProgressBar

    private var homeadapter: HomeMovieAdapter? = null
    private val TAG = HomeMovieActivity::class.java.getSimpleName()
    private var requesQueue: RequestQueue? = null


    var btns = arrayOfNulls<Button>(6)


    var model_film: ArrayList<MFilm> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_home_movie)

        HttpsTrustManager.allowAllSSL()


        rv_film = findViewById(R.id.rvListMovie)
        pg_film = findViewById(R.id.pbHome)
        pg_film = findViewById(R.id.pbHome)


        btns[0] = findViewById(R.id.btnGenreNowPlaying)
        btns[1] = findViewById(R.id.btnGenreAction)
        btns[2] = findViewById(R.id.btnAnimasiAction)
        btns[3] = findViewById(R.id.btnAdvAction)
        btns[4] = findViewById(R.id.btnKomediAction)
        btns[5] = findViewById(R.id.btnHororAction)

        news.setOnClickListener {

            val intent = Intent(applicationContext, HomeNewsActivity::class.java)
            startActivity(intent)

        }

        requesQueue = Volley.newRequestQueue(applicationContext)

        rv_film.setLayoutManager(GridLayoutManager(this, 2) as RecyclerView.LayoutManager?)

        model_film = ArrayList()

        btns[0]?.setBackgroundResource(R.drawable.editext_biru)
        btns[0]?.setTextColor(Color.WHITE)
        getTayangSekarang()


        for (x in btns.indices) {
            //change its color
            btns[0]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (v.isClickable) {


                        getTayangSekarang()

                        btns[0]?.setBackgroundResource(R.drawable.editext_biru)
                        btns[0]?.setTextColor(Color.WHITE)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)

                        btns[5]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[5]?.setTextColor(Color.BLACK)


                    } else {
                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)

                        btns[5]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[5]?.setTextColor(Color.BLACK)


                    }
                }

            })

            btns[1]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (v.isClickable) {
                        getGenreAksi()

                        btns[1]?.setBackgroundResource(R.drawable.editext_biru)
                        btns[1]?.setTextColor(Color.WHITE)

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)

                        btns[5]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[5]?.setTextColor(Color.BLACK)


                    } else {

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)

                        btns[5]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[5]?.setTextColor(Color.BLACK)

                    }
                }

            })

            btns[2]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (v.isClickable) {
                        getGenreAnimasi()

                        btns[2]?.setBackgroundResource(R.drawable.editext_biru)
                        btns[2]?.setTextColor(Color.WHITE)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)

                        btns[5]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[5]?.setTextColor(Color.BLACK)


                    } else {

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)


                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)

                        btns[5]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[5]?.setTextColor(Color.BLACK)

                    }
                }

            })


            btns[3]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (v.isClickable) {
                        getGenrePetualangan()

                        btns[3]?.setBackgroundResource(R.drawable.editext_biru)
                        btns[3]?.setTextColor(Color.WHITE)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)

                        btns[5]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[5]?.setTextColor(Color.BLACK)


                    } else {

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)

                        btns[5]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[5]?.setTextColor(Color.BLACK)

                    }
                }

            })

            btns[4]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (v.isClickable) {
                        getGenreKomedi()

                        btns[4]?.setBackgroundResource(R.drawable.editext_biru)
                        btns[4]?.setTextColor(Color.WHITE)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[5]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[5]?.setTextColor(Color.BLACK)


                    } else {

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[5]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[5]?.setTextColor(Color.BLACK)

                    }
                }

            })

            btns[5]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (v.isClickable) {
                        getGenreHoror()

                        btns[5]?.setBackgroundResource(R.drawable.editext_biru)
                        btns[5]?.setTextColor(Color.WHITE)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)


                    } else {

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)

                    }
                }

            })


        }

    }

    fun getTayangSekarang() {


        if (model_film.size > 0) {
            model_film.clear()

        }


        pg_film.setVisibility(View.VISIBLE)

        val requestshowmovie = JsonObjectRequest(
            Request.Method.GET,
            ServerMovieHost.BASEURL + ServerMovieHost.MOVIE_PLAYNOW + ServerMovieHost.APIKEY,
            null,
            object : Response.Listener<JSONObject?> {
                override fun onResponse(response: JSONObject?) {

                    Log.e("TAG", response.toString())
                    Log.e(TAG, "History Response: $response")

                    try {

                        pg_film.setVisibility(View.GONE)


                        var jsonArray = response?.getJSONArray("results")

                        for (i in 0 until jsonArray!!.length()) {


                            val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                            var data_film = MFilm()



                            data_film.poster_path = jsonObject.getString("poster_path")
                            data_film.overview = jsonObject.getString("overview")
                            data_film.title = jsonObject.getString("original_title")
                            data_film.vote_average = jsonObject.getDouble("vote_average")

                            data_film.id = jsonObject.getInt("id")


                            (model_film as ArrayList<MFilm>).add(data_film)


                            homeadapter =
                                HomeMovieAdapter(
                                    applicationContext,
                                    model_film
                                )

                            rv_film.adapter = homeadapter

                            homeadapter!!.setListener(object : ItemClickListener<MFilm> {
                                override fun onClicked(mfilm: MFilm, position: Int, view: View?) {

                                    val intent = Intent(
                                        applicationContext,
                                        DetailMovieActivity::class.java
                                    )
                                    intent.putExtra("data", Gson().toJson(mfilm))
                                    startActivity(intent)

                                }
                            })

                        }

                        Log.e(TAG, "Movie Response: $response")


                    } catch (e: JSONException) {
                        pg_film.setVisibility(View.GONE)
                        e.printStackTrace()
                        Toast.makeText(applicationContext, "Gagal ", Toast.LENGTH_LONG).show()
                    }


                }


            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    error.printStackTrace()
                }
            })
        requesQueue?.add(requestshowmovie)


    }

    fun getGenreAksi() {


        if (model_film.size > 0) {
            model_film.clear()

        }


        pg_film.setVisibility(View.VISIBLE)

        val requestGenreAksi = JsonObjectRequest(
            Request.Method.GET,
            ServerMovieHost.BASEURL + ServerMovieHost.MOVIE_POPULAR + ServerMovieHost.APIKEY + ServerMovieHost.GENRE_ACTION,
            null,
            object : Response.Listener<JSONObject?> {
                override fun onResponse(response: JSONObject?) {

                    Log.e("Genre Aksi", response.toString())
                    Log.e(TAG, "Genre Aksi Response: $response")

                    try {
                        pg_film.setVisibility(View.GONE)


                        var jsonArray = response?.getJSONArray("results")

                        for (i in 0 until jsonArray!!.length()) {


                            val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                            var data_film = MFilm()



                            data_film.poster_path = jsonObject.getString("poster_path")
                            data_film.overview = jsonObject.getString("overview")
                            data_film.title = jsonObject.getString("original_title")
                            data_film.vote_average = jsonObject.getDouble("vote_average")

                            data_film.id = jsonObject.getInt("id")


                            (model_film as ArrayList<MFilm>).add(data_film)


                            homeadapter =
                                HomeMovieAdapter(
                                    applicationContext,
                                    model_film
                                )

                            rv_film.adapter = homeadapter

                            homeadapter!!.setListener(object : ItemClickListener<MFilm> {
                                override fun onClicked(mfilm: MFilm, position: Int, view: View?) {

                                    val intent = Intent(
                                        applicationContext,
                                        DetailMovieActivity::class.java
                                    )
                                    intent.putExtra("data", Gson().toJson(mfilm))
                                    startActivity(intent)

                                }
                            })

                        }

                        Log.e(TAG, "Movie Response: $response")


                    } catch (e: JSONException) {
                        pg_film.setVisibility(View.GONE)
                        e.printStackTrace()
                        Toast.makeText(applicationContext, "Gagal ", Toast.LENGTH_LONG).show()
                    }


                }


            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    error.printStackTrace()
                }
            })
        requesQueue?.add(requestGenreAksi)


    }

    fun getGenreAnimasi() {


        if (model_film.size > 0) {
            model_film.clear()

        }
        pg_film.setVisibility(View.VISIBLE)

        val requestGenreAksi = JsonObjectRequest(
            Request.Method.GET,
            ServerMovieHost.BASEURL + ServerMovieHost.MOVIE_POPULAR + ServerMovieHost.APIKEY + ServerMovieHost.GENRE_ANIMASI,
            null,
            object : Response.Listener<JSONObject?> {
                override fun onResponse(response: JSONObject?) {

                    Log.e("Genre Animasi", response.toString())
                    Log.e(TAG, "Genre Animasi Response: $response")

                    try {
                        pg_film.setVisibility(View.GONE)


                        var jsonArray = response?.getJSONArray("results")

                        for (i in 0 until jsonArray!!.length()) {


                            val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                            var data_film = MFilm()



                            data_film.poster_path = jsonObject.getString("poster_path")
                            data_film.overview = jsonObject.getString("overview")
                            data_film.title = jsonObject.getString("original_title")
                            data_film.vote_average = jsonObject.getDouble("vote_average")

                            data_film.id = jsonObject.getInt("id")


                            (model_film as ArrayList<MFilm>).add(data_film)


                            homeadapter =
                                HomeMovieAdapter(
                                    applicationContext,
                                    model_film
                                )

                            rv_film.adapter = homeadapter

                            homeadapter!!.setListener(object : ItemClickListener<MFilm> {
                                override fun onClicked(mfilm: MFilm, position: Int, view: View?) {

                                    val intent = Intent(
                                        applicationContext,
                                        DetailMovieActivity::class.java
                                    )
                                    intent.putExtra("data", Gson().toJson(mfilm))
                                    startActivity(intent)

                                }
                            })

                        }

                        Log.e(TAG, "Movie Response: $response")


                    } catch (e: JSONException) {
                        pg_film.setVisibility(View.GONE)
                        e.printStackTrace()
                        Toast.makeText(applicationContext, "Gagal ", Toast.LENGTH_LONG).show()
                    }


                }


            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    error.printStackTrace()
                }
            })
        requesQueue?.add(requestGenreAksi)


    }

    fun getGenrePetualangan() {


        if (model_film.size > 0) {
            model_film.clear()

        }


        pg_film.setVisibility(View.VISIBLE)

        val requestGenreAksi = JsonObjectRequest(
            Request.Method.GET,
            ServerMovieHost.BASEURL + ServerMovieHost.MOVIE_POPULAR + ServerMovieHost.APIKEY + ServerMovieHost.GENRE_ADVENTURE,
            null,
            object : Response.Listener<JSONObject?> {
                override fun onResponse(response: JSONObject?) {

                    Log.e("Genre Petualangan", response.toString())
                    Log.e(TAG, "Genre Petualangan Response: $response")

                    try {
                        pg_film.setVisibility(View.GONE)


                        var jsonArray = response?.getJSONArray("results")

                        for (i in 0 until jsonArray!!.length()) {


                            val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                            var data_film = MFilm()



                            data_film.poster_path = jsonObject.getString("poster_path")
                            data_film.overview = jsonObject.getString("overview")
                            data_film.title = jsonObject.getString("original_title")
                            data_film.vote_average = jsonObject.getDouble("vote_average")

                            data_film.id = jsonObject.getInt("id")


                            (model_film as ArrayList<MFilm>).add(data_film)


                            homeadapter =
                                HomeMovieAdapter(
                                    applicationContext,
                                    model_film
                                )

                            rv_film.adapter = homeadapter

                            homeadapter!!.setListener(object : ItemClickListener<MFilm> {
                                override fun onClicked(mfilm: MFilm, position: Int, view: View?) {

                                    val intent = Intent(
                                        applicationContext,
                                        DetailMovieActivity::class.java
                                    )
                                    intent.putExtra("data", Gson().toJson(mfilm))
                                    startActivity(intent)

                                }
                            })

                        }

                        Log.e(TAG, "Movie Response: $response")


                    } catch (e: JSONException) {
                        pg_film.setVisibility(View.GONE)
                        e.printStackTrace()
                        Toast.makeText(applicationContext, "Gagal ", Toast.LENGTH_LONG).show()
                    }


                }


            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    error.printStackTrace()
                }
            })
        requesQueue?.add(requestGenreAksi)


    }

    fun getGenreKomedi() {


        if (model_film.size > 0) {
            model_film.clear()

        }


        pg_film.setVisibility(View.VISIBLE)

        val requestGenreAksi = JsonObjectRequest(
            Request.Method.GET,
            ServerMovieHost.BASEURL + ServerMovieHost.MOVIE_POPULAR + ServerMovieHost.APIKEY + ServerMovieHost.GENRE_KOMEDI,
            null,
            object : Response.Listener<JSONObject?> {
                override fun onResponse(response: JSONObject?) {

                    Log.e("Genre Komedi", response.toString())
                    Log.e(TAG, "Genre Komedi Response: $response")

                    try {
                        pg_film.setVisibility(View.GONE)

                        var jsonArray = response?.getJSONArray("results")

                        for (i in 0 until jsonArray!!.length()) {


                            val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                            var data_film = MFilm()



                            data_film.poster_path = jsonObject.getString("poster_path")
                            data_film.overview = jsonObject.getString("overview")
                            data_film.title = jsonObject.getString("original_title")
                            data_film.vote_average = jsonObject.getDouble("vote_average")

                            data_film.id = jsonObject.getInt("id")


                            (model_film as ArrayList<MFilm>).add(data_film)


                            homeadapter =
                                HomeMovieAdapter(
                                    applicationContext,
                                    model_film
                                )

                            rv_film.adapter = homeadapter

                            homeadapter!!.setListener(object : ItemClickListener<MFilm> {
                                override fun onClicked(mfilm: MFilm, position: Int, view: View?) {

                                    val intent = Intent(
                                        applicationContext,
                                        DetailMovieActivity::class.java
                                    )
                                    intent.putExtra("data", Gson().toJson(mfilm))
                                    startActivity(intent)

                                }
                            })

                        }

                        Log.e(TAG, "Movie Response: $response")


                    } catch (e: JSONException) {
                        pg_film.setVisibility(View.GONE)
                        e.printStackTrace()
                        Toast.makeText(applicationContext, "Gagal ", Toast.LENGTH_LONG).show()
                    }


                }


            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    error.printStackTrace()
                }
            })
        requesQueue?.add(requestGenreAksi)


    }

    fun getGenreHoror() {


        if (model_film.size > 0) {
            model_film.clear()

        }


        pg_film.setVisibility(View.VISIBLE)

        val requestGenreAksi = JsonObjectRequest(
            Request.Method.GET,
            ServerMovieHost.BASEURL + ServerMovieHost.MOVIE_POPULAR + ServerMovieHost.APIKEY + ServerMovieHost.GENRE_HORROR,
            null,
            object : Response.Listener<JSONObject?> {
                override fun onResponse(response: JSONObject?) {

                    Log.e("Genre Horor", response.toString())
                    Log.e(TAG, "Genre Horor Response: $response")

                    try {
                        pg_film.setVisibility(View.GONE)

                        var jsonArray = response?.getJSONArray("results")

                        for (i in 0 until jsonArray!!.length()) {


                            val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                            var data_film = MFilm()



                            data_film.poster_path = jsonObject.getString("poster_path")
                            data_film.overview = jsonObject.getString("overview")
                            data_film.title = jsonObject.getString("original_title")
                            data_film.vote_average = jsonObject.getDouble("vote_average")

                            data_film.id = jsonObject.getInt("id")


                            (model_film as ArrayList<MFilm>).add(data_film)


                            homeadapter =
                                HomeMovieAdapter(
                                    applicationContext,
                                    model_film
                                )

                            rv_film.adapter = homeadapter

                            homeadapter!!.setListener(object : ItemClickListener<MFilm> {
                                override fun onClicked(mfilm: MFilm, position: Int, view: View?) {

                                    val intent = Intent(
                                        applicationContext,
                                        DetailMovieActivity::class.java
                                    )
                                    intent.putExtra("data", Gson().toJson(mfilm))
                                    startActivity(intent)

                                }
                            })

                        }

                        Log.e(TAG, "Movie Response: $response")


                    } catch (e: JSONException) {
                        pg_film.setVisibility(View.GONE)
                        e.printStackTrace()
                        Toast.makeText(applicationContext, "Gagal ", Toast.LENGTH_LONG).show()
                    }


                }


            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    error.printStackTrace()
                }
            })
        requesQueue?.add(requestGenreAksi)


    }


}