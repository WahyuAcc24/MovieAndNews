package com.wr15.mytestproject.acticity.newspaper

import android.app.ProgressDialog
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.wr15.mytestproject.AppController
import com.wr15.mytestproject.R
import com.wr15.mytestproject.adapter.HomeNewsAdapter
import com.wr15.mytestproject.adapter.ItemClickListener
import com.wr15.mytestproject.model.MNews
import com.wr15.mytestproject.server.HttpsTrustManager
import com.wr15.mytestproject.server.ServerNewsHost
import io.isfaaghyth.rak.Rak
import kotlinx.android.synthetic.main.lay_bs_review.*
import org.json.JSONException
import org.json.JSONObject


class HomeNewsActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var rv_news: RecyclerView
    private lateinit var pg_news: ProgressBar

    private var newsAdapter: HomeNewsAdapter? = null
    private val TAG = HomeNewsActivity::class.java.getSimpleName()
    private var requesQueue: RequestQueue? = null

    lateinit var swipeRefreshLayout : SwipeRefreshLayout

    var tag_json_obj = "json_obj_req"


    var btns = arrayOfNulls<Button>(5)


    var model_news: ArrayList<MNews> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_home_news)

        HttpsTrustManager.allowAllSSL()


        rv_news = findViewById(R.id.rvListNews)
        pg_news = findViewById(R.id.pbHomeNews)


        btns[0] = findViewById(R.id.btnTopheadline)
        btns[1] = findViewById(R.id.btnApple)
        btns[2] = findViewById(R.id.btnTesla)
        btns[3] = findViewById(R.id.btnBisnisNews)
        btns[4] = findViewById(R.id.btnWallStreet)

        swipeRefreshLayout = findViewById(R.id.swipeNews)


        requesQueue = Volley.newRequestQueue(applicationContext)

        rv_news.setLayoutManager(LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?)

        model_news = ArrayList()

        btns[0]?.setBackgroundResource(R.drawable.editext_biru)
        btns[0]?.setTextColor(Color.WHITE)
        getTopHeadline()

        swipeRefreshLayout.setColorSchemeResources(R.color.biru_muda, R.color.merah, R.color.black)



        swipeRefreshLayout.setOnRefreshListener(object: SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                // Handler untuk menjalankan jeda selama 5 detik
                Handler().postDelayed(object:Runnable {
                    override fun run() {
                        // Berhenti berputar/refreshing
                        swipeRefreshLayout.setRefreshing(false)
                        getTopHeadline()

                    }
                }, 2000)
            }
        })



        for (x in btns.indices) {
            //change its color
            btns[0]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (v.isClickable) {


                        getTopHeadline()

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


                    } else {
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

            btns[1]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (v.isClickable) {
                        getAppleNews()

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


                    } else {

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)

                    }
                }

            })

            btns[2]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (v.isClickable) {
                        getTeslaNews()

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


                    } else {

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)


                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)


                    }
                }

            })


            btns[3]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (v.isClickable) {
                        getBisnisNews()

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


                    } else {

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[4]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[4]?.setTextColor(Color.BLACK)

                    }
                }

            })

            btns[4]?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (v.isClickable) {
                        getWallStreetNews()

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


                    } else {

                        btns[0]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[0]?.setTextColor(Color.BLACK)

                        btns[1]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[1]?.setTextColor(Color.BLACK)

                        btns[2]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[2]?.setTextColor(Color.BLACK)

                        btns[3]?.setBackgroundResource(R.drawable.editext_putih)
                        btns[3]?.setTextColor(Color.BLACK)


                    }
                }

            })

        }

    }


    fun getTopHeadline() {


        if (model_news.size > 0) {
            model_news.clear()

        }


        pg_news.setVisibility(View.VISIBLE)

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, ServerNewsHost.WEB_HEADLINE + ServerNewsHost.API_KEY, null,
            { response ->
                Log.e(TAG, "Login Response: $response")

                pg_news.setVisibility(View.GONE)

                try {

                    val responseobject: JSONObject = JSONObject(response.toString())

                    var jsonArray = responseobject.getJSONArray("articles")

                    for (i in 0 until jsonArray.length()) {


                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                        val nama_sumber = jsonObject.getJSONObject("source").getString("name")

                        val penulis = jsonObject.getString("author")
                        val tanggal = jsonObject.getString("publishedAt")
                        val kontent = jsonObject.getString("content")
                        val gambar = jsonObject.getString("urlToImage")
                        val url_berita = jsonObject.getString("url")
                        val deskripsi = jsonObject.getString("description")
                        val judul = jsonObject.getString("title")

                        var data_news = MNews()

                        data_news.setName(nama_sumber)
                        data_news.setContent(kontent)
                        data_news.setUrlToImage(gambar)
                        data_news.setUrl(url_berita)
                        data_news.setDescription(deskripsi)
                        data_news.setTitle(judul)
                        data_news.setAuthor(penulis)
                        data_news.setPublishedAt(tanggal)



                        model_news.add(data_news)


                        newsAdapter =
                            HomeNewsAdapter(
                                applicationContext,
                                model_news
                            )

                        rv_news.adapter = newsAdapter
                        newsAdapter!!.setListener(object : ItemClickListener<MNews> {
                            override fun onClicked(mnews: MNews, position: Int, view: View?) {

                                val intent = Intent(
                                    applicationContext,
                                    DetailNewsActivity::class.java
                                )
                                intent.putExtra("datanews", Gson().toJson(mnews))
                                startActivity(intent)

                            }
                        })
                    }

                } catch (e: JSONException) {
                    pg_news.setVisibility(View.GONE)
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "Gagal ", Toast.LENGTH_LONG).show()
                }


            },

            {
                Log.d("Error occur", "Try again..." + it.networkResponse.statusCode)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        //adding request to queue
        requesQueue?.add(jsonObjectRequest)


    }

    fun getAppleNews() {


        if (model_news.size > 0) {
            model_news.clear()

        }


        pg_news.setVisibility(View.VISIBLE)
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, ServerNewsHost.WEB_APPLE + ServerNewsHost.API_KEY, null,
            { response ->
                Log.e(TAG, "Login Response: $response")

                pg_news.setVisibility(View.GONE)

                try {

                    val responseobject: JSONObject = JSONObject(response.toString())

                    var jsonArray = responseobject.getJSONArray("articles")

                    for (i in 0 until jsonArray.length()) {


                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                        val nama_sumber = jsonObject.getJSONObject("source").getString("name")

                        val penulis = jsonObject.getString("author")
                        val tanggal = jsonObject.getString("publishedAt")
                        val kontent = jsonObject.getString("content")
                        val gambar = jsonObject.getString("urlToImage")
                        val url_berita = jsonObject.getString("url")
                        val deskripsi = jsonObject.getString("description")
                        val judul = jsonObject.getString("title")

                        var data_news = MNews()

                        data_news.setName(nama_sumber)
                        data_news.setContent(kontent)
                        data_news.setUrlToImage(gambar)
                        data_news.setUrl(url_berita)
                        data_news.setDescription(deskripsi)
                        data_news.setTitle(judul)
                        data_news.setAuthor(penulis)
                        data_news.setPublishedAt(tanggal)


                        model_news.add(data_news)


                        newsAdapter =
                            HomeNewsAdapter(
                                applicationContext,
                                model_news
                            )

                        rv_news.adapter = newsAdapter
                        newsAdapter!!.setListener(object : ItemClickListener<MNews> {
                            override fun onClicked(mnews: MNews, position: Int, view: View?) {

                                val intent = Intent(
                                    applicationContext,
                                    DetailNewsActivity::class.java
                                )
                                intent.putExtra("datanews", Gson().toJson(mnews))
                                startActivity(intent)

                            }
                        })
                    }

                } catch (e: JSONException) {
                    pg_news.setVisibility(View.GONE)
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "Gagal ", Toast.LENGTH_LONG).show()
                }


            },

            {
                Log.d("Error occur", "Try again..." + it.networkResponse.statusCode)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        //adding request to queue
        requesQueue?.add(jsonObjectRequest)


    }

    fun getTeslaNews() {


        if (model_news.size > 0) {
            model_news.clear()

        }


        pg_news.setVisibility(View.VISIBLE)

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, ServerNewsHost.WEB_TESLA + ServerNewsHost.API_KEY, null,
            { response ->
                Log.e(TAG, "Login Response: $response")

                pg_news.setVisibility(View.GONE)

                try {

                    val responseobject: JSONObject = JSONObject(response.toString())

                    var jsonArray = responseobject.getJSONArray("articles")

                    for (i in 0 until jsonArray.length()) {


                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                        val nama_sumber = jsonObject.getJSONObject("source").getString("name")

                        val penulis = jsonObject.getString("author")
                        val tanggal = jsonObject.getString("publishedAt")
                        val kontent = jsonObject.getString("content")
                        val gambar = jsonObject.getString("urlToImage")
                        val url_berita = jsonObject.getString("url")
                        val deskripsi = jsonObject.getString("description")
                        val judul = jsonObject.getString("title")

                        var data_news = MNews()

                        data_news.setName(nama_sumber)
                        data_news.setContent(kontent)
                        data_news.setUrlToImage(gambar)
                        data_news.setUrl(url_berita)
                        data_news.setDescription(deskripsi)
                        data_news.setTitle(judul)
                        data_news.setAuthor(penulis)
                        data_news.setPublishedAt(tanggal)


                        model_news.add(data_news)


                        newsAdapter =
                            HomeNewsAdapter(
                                applicationContext,
                                model_news
                            )

                        rv_news.adapter = newsAdapter
                        newsAdapter!!.setListener(object : ItemClickListener<MNews> {
                            override fun onClicked(mnews: MNews, position: Int, view: View?) {

                                val intent = Intent(
                                    applicationContext,
                                    DetailNewsActivity::class.java
                                )
                                intent.putExtra("datanews", Gson().toJson(mnews))
                                startActivity(intent)

                            }
                        })
                    }

                } catch (e: JSONException) {
                    pg_news.setVisibility(View.GONE)
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "Gagal ", Toast.LENGTH_LONG).show()
                }


            },

            {
                Log.d("Error occur", "Try again..." + it.networkResponse.statusCode)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        //adding request to queue
        requesQueue?.add(jsonObjectRequest)


    }

    fun getBisnisNews() {

        if (model_news.size > 0) {
            model_news.clear()

        }


        pg_news.setVisibility(View.VISIBLE)

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, ServerNewsHost.WEB_BISNIS + ServerNewsHost.API_KEY, null,
            { response ->
                Log.e(TAG, "Login Response: $response")

                pg_news.setVisibility(View.GONE)

                try {

                    val responseobject: JSONObject = JSONObject(response.toString())

                    var jsonArray = responseobject.getJSONArray("articles")

                    for (i in 0 until jsonArray.length()) {


                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                        val nama_sumber = jsonObject.getJSONObject("source").getString("name")

                        val penulis = jsonObject.getString("author")
                        val tanggal = jsonObject.getString("publishedAt")
                        val kontent = jsonObject.getString("content")
                        val gambar = jsonObject.getString("urlToImage")
                        val url_berita = jsonObject.getString("url")
                        val deskripsi = jsonObject.getString("description")
                        val judul = jsonObject.getString("title")

                        var data_news = MNews()

                        data_news.setName(nama_sumber)
                        data_news.setContent(kontent)
                        data_news.setUrlToImage(gambar)
                        data_news.setUrl(url_berita)
                        data_news.setDescription(deskripsi)
                        data_news.setTitle(judul)
                        data_news.setAuthor(penulis)
                        data_news.setPublishedAt(tanggal)

                        model_news.add(data_news)


                        newsAdapter =
                            HomeNewsAdapter(
                                applicationContext,
                                model_news
                            )

                        rv_news.adapter = newsAdapter
                        newsAdapter!!.setListener(object : ItemClickListener<MNews> {
                            override fun onClicked(mnews: MNews, position: Int, view: View?) {

                                val intent = Intent(
                                    applicationContext,
                                    DetailNewsActivity::class.java
                                )
                                intent.putExtra("datanews", Gson().toJson(mnews))
                                startActivity(intent)

                            }
                        })
                    }

                } catch (e: JSONException) {
                    pg_news.setVisibility(View.GONE)
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "Gagal ", Toast.LENGTH_LONG).show()
                }


            },

            {
                Log.d("Error occur", "Try again..." + it.networkResponse.statusCode)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        //adding request to queue
        requesQueue?.add(jsonObjectRequest)


    }

    fun getWallStreetNews() {


        if (model_news.size > 0) {
            model_news.clear()

        }


        pg_news.setVisibility(View.VISIBLE)

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, ServerNewsHost.WEB_WALL_STREET + ServerNewsHost.API_KEY, null,
            { response ->
                Log.e(TAG, "Login Response: $response")

                pg_news.setVisibility(View.GONE)

                try {

                    val responseobject: JSONObject = JSONObject(response.toString())

                    var jsonArray = responseobject.getJSONArray("articles")

                    for (i in 0 until jsonArray.length()) {


                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                        val nama_sumber = jsonObject.getJSONObject("source").getString("name")

                        val penulis = jsonObject.getString("author")
                        val tanggal = jsonObject.getString("publishedAt")
                        val kontent = jsonObject.getString("content")
                        val gambar = jsonObject.getString("urlToImage")
                        val url_berita = jsonObject.getString("url")
                        val deskripsi = jsonObject.getString("description")
                        val judul = jsonObject.getString("title")

                        var data_news = MNews()

                        data_news.setName(nama_sumber)
                        data_news.setContent(kontent)
                        data_news.setUrlToImage(gambar)
                        data_news.setUrl(url_berita)
                        data_news.setDescription(deskripsi)
                        data_news.setTitle(judul)
                        data_news.setAuthor(penulis)
                        data_news.setPublishedAt(tanggal)

                        model_news.add(data_news)


                        newsAdapter =
                            HomeNewsAdapter(
                                applicationContext,
                                model_news
                            )

                        rv_news.adapter = newsAdapter
                        newsAdapter!!.setListener(object : ItemClickListener<MNews> {
                            override fun onClicked(mnews: MNews, position: Int, view: View?) {

                                val intent = Intent(
                                    applicationContext,
                                    DetailNewsActivity::class.java
                                )
                                intent.putExtra("datanews", Gson().toJson(mnews))
                                startActivity(intent)

                            }
                        })
                    }

                } catch (e: JSONException) {
                    pg_news.setVisibility(View.GONE)
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "Gagal ", Toast.LENGTH_LONG).show()
                }


            },

            {
                Log.d("Error occur", "Try again..." + it.networkResponse.statusCode)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        //adding request to queue
        requesQueue?.add(jsonObjectRequest)


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu_search, menu)
        val item: MenuItem = menu!!.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(item) as SearchView
        searchView.setIconified(true)


//        MenuItemCompat.setShowAsAction(item,MenuItemCompat.SHOW_AS_ACTION_ALWAYS)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager


        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()))

        searchView.setOnQueryTextListener(this)

//        searchView.setQueryHint("Cari....")
        return super.onCreateOptionsMenu(menu)

    }

    override fun onQueryTextSubmit(query: String): Boolean {

        cariData(query)

        return false

    }

    override fun onQueryTextChange(newText: String): Boolean {

        return false
    }

    private fun cariData(keyword: String) {

        if (model_news.size > 0) {
            model_news.clear()

        }


        val loading = ProgressDialog(this)
        loading.setCancelable(false)
        loading.setMessage("Mohon Tunggu...")
        loading.show()

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, ServerNewsHost.SEARCH + ServerNewsHost.API_SEARCH + "&q=" + keyword, null,
            { response ->
                Log.e(TAG, "Login Response: $response")

                pg_news.setVisibility(View.GONE)

                try {

                    loading.hide()

                    val responseobject: JSONObject = JSONObject(response.toString())

                    var jsonArray = responseobject.getJSONArray("articles")

                    for (i in 0 until jsonArray.length()) {


                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                        val nama_sumber = jsonObject.getJSONObject("source").getString("name")

                        val penulis = jsonObject.getString("author")
                        val tanggal = jsonObject.getString("publishedAt")
                        val kontent = jsonObject.getString("content")
                        val gambar = jsonObject.getString("urlToImage")
                        val url_berita = jsonObject.getString("url")
                        val deskripsi = jsonObject.getString("description")
                        val judul = jsonObject.getString("title")

                        var data_news = MNews()

                        data_news.setName(nama_sumber)
                        data_news.setContent(kontent)
                        data_news.setUrlToImage(gambar)
                        data_news.setUrl(url_berita)
                        data_news.setDescription(deskripsi)
                        data_news.setTitle(judul)
                        data_news.setAuthor(penulis)
                        data_news.setPublishedAt(tanggal)

                        model_news.add(data_news)


                        newsAdapter =
                            HomeNewsAdapter(
                                applicationContext,
                                model_news
                            )

                        rv_news.adapter = newsAdapter
                        newsAdapter!!.setListener(object : ItemClickListener<MNews> {
                            override fun onClicked(mnews: MNews, position: Int, view: View?) {

                                val intent = Intent(
                                    applicationContext,
                                    DetailNewsActivity::class.java
                                )
                                intent.putExtra("datanews", Gson().toJson(mnews))
                                startActivity(intent)

                            }
                        })
                    }

                } catch (e: JSONException) {
                    loading.hide()
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "Data tidak ditemukan ", Toast.LENGTH_LONG).show()
                }


            },

            {
                Log.d("Error occur", "Try again..." + it.networkResponse.statusCode)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        //adding request to queue
        requesQueue?.add(jsonObjectRequest)

    }


}