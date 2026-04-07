package com.example.a517lablearnandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a517lablearnandroid.ui.theme._517LabLearnAndroidTheme

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _517LabLearnAndroidTheme {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, RPGCardActivity::class.java))
                        }) {
                            Text(text = "RPGCardActivity")
                        }
                    }
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, MainActivity2::class.java))
                        }) {
                            Text(text = "MainActivity2 (Lifecycle Demo)")
                        }
                    }
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, ListActivity::class.java))
                        }) {
                            Text(text = "ListActivity (Pokemon)")
                        }
                    }
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, SharedPreferencesActivity::class.java))
                        }) {
                            Text(text = "SharedPreferencesActivity")
                        }
                    }
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, Mission1LikeButtonActivity::class.java))
                        }) {
                            Text(text = "Mission 1: Like Button Animation")
                        }
                    }
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, Mission2ContactListActivity::class.java))
                        }) {
                            Text(text = "Mission 2: Contact List + Pagination")
                        }
                    }
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, Mission3DonutChartActivity::class.java))
                        }) {
                            Text(text = "Mission 3: Donut Chart (Canvas)")
                        }
                    }
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, Mission4ToDoListActivity::class.java))
                        }) {
                            Text(text = "Mission 4: To-Do List (Swipe to Dismiss)")
                        }
                    }
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, Mission5SideEffectsActivity::class.java))
                        }) {
                            Text(text = "Mission 5: Side Effects (Snackbar)")
                        }
                    }
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, Mission6WebViewActivity::class.java))
                        }) {
                            Text(text = "Mission 6: WebView (AndroidView)")
                        }
                    }
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, Mission7TransitionActivity::class.java))
                        }) {
                            Text(text = "Mission 7: Activity Transitions")
                        }
                    }
                    item {
                        Button(onClick = {
                            startActivity(Intent(this@MenuActivity, Mission8AdaptiveLayoutActivity::class.java))
                        }) {
                            Text(text = "Mission 8: Adaptive Layout")
                        }
                    }
                }
            }
        }
    }
}

