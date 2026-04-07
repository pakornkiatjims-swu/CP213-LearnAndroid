package com.example.a517lablearnandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import com.example.a517lablearnandroid.ui.theme._517LabLearnAndroidTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ---- ViewModel ----

class WebViewViewModel : ViewModel() {
    private val _url = MutableStateFlow("https://www.google.com")
    val url: StateFlow<String> = _url.asStateFlow()

    fun updateUrl(newUrl: String) {
        val sanitized = when {
            newUrl.startsWith("https://") || newUrl.startsWith("http://") -> newUrl
            newUrl.isNotBlank() -> "https://$newUrl"
            else -> return
        }
        _url.value = sanitized
    }
}

// ---- Activity ----

class Mission6WebViewActivity : ComponentActivity() {
    private val viewModel: WebViewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _517LabLearnAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WebViewScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun WebViewScreen(
    viewModel: WebViewViewModel,
    modifier: Modifier = Modifier
) {
    val currentUrl by viewModel.url.collectAsState()
    var inputUrl by remember { mutableStateOf(currentUrl) }

    Column(modifier = modifier.fillMaxSize()) {
        // URL input bar + Go button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TextField(
                value = inputUrl,
                onValueChange = { inputUrl = it },
                modifier = Modifier.weight(1f),
                label = { Text("URL") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
                keyboardActions = KeyboardActions(onGo = { viewModel.updateUrl(inputUrl) })
            )
            Button(
                onClick = { viewModel.updateUrl(inputUrl) },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Go")
            }
        }

        // WebView ฝังด้วย AndroidView
        AndroidView(
            factory = { context ->
                @SuppressLint("SetJavaScriptEnabled")
                val webView = WebView(context).apply {
                    webViewClient = WebViewClient() // โหลดภายในแอป ไม่เด้งออก Browser
                    settings.javaScriptEnabled = true
                    loadUrl(currentUrl)
                }
                webView
            },
            update = { webView ->
                // เมื่อ currentUrl เปลี่ยน ให้ WebView โหลด URL ใหม่
                webView.loadUrl(currentUrl)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}
