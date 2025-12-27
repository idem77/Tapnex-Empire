package com.tapnexempire.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity

class LudoWebViewActivity : ComponentActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        setContentView(webView)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        webView.addJavascriptInterface(GameBridge(), "AndroidBridge")

        // 🔗 TEMP GAME URL (replace later)
        webView.loadUrl("https://your-ludo-game-url.com")
    }

    inner class GameBridge {

        @JavascriptInterface
        fun onGameFinished(score: Int) {
            val resultIntent = intent
            resultIntent.putExtra("GAME_SCORE", score)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
