package com.tapnexempire.ui.game

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GameScreen(

    gameUrl: String
) {

    AndroidView(

        factory = { context ->

            WebView(context).apply {

                settings.javaScriptEnabled = true

                settings.domStorageEnabled = true

                webViewClient = WebViewClient()

                webChromeClient = WebChromeClient()

                loadUrl(gameUrl)
            }
        }
    )
}
