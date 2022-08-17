package com.wr15.mytestproject.acticity.newspaper

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wr15.mytestproject.R

class WebNewsActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    lateinit var img : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_web)

        webView = findViewById(R.id.webview)
        webView.settings.setJavaScriptEnabled(true)

        img = findViewById(R.id.imgBackWebview)

        img.setOnClickListener {
            finish()
        }

        val intent = intent

        val url_web = intent.getStringExtra("urlnews")


        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        webView.loadUrl(url_web)


    }

}