package com.tapnexempire.ui.ludo

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.viewmodel.LudoViewModel

@Composable
fun LudoWebViewScreen(
    tournamentId: String,
    userId: String,
    onScoreSubmit: () -> Unit,
    ludoViewModel: LudoViewModel = hiltViewModel()
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl("https://your-ludo-game-web-url.com") // Replace with your Ludo Web URL
            }
        },
        update = { webView ->
            // Example: Trigger score submit from JS or button
            val finalScore = 100 // Replace with actual score from Web
            ludoViewModel.submitScore(userId, tournamentId, finalScore)

            ludoViewModel.scoreSubmitState.collect { success ->
                if (success) onScoreSubmit()
            }
        }
    )
}
