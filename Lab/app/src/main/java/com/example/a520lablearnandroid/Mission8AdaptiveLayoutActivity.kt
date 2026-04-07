package com.example.a520lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a520lablearnandroid.ui.theme._517LabLearnAndroidTheme

class Mission8AdaptiveLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _517LabLearnAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AdaptiveProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AdaptiveProfileScreen(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        if (maxWidth < 600.dp) {
            // แนวตั้ง / มือถือ: Column (รูปอยู่บน ข้อมูลอยู่ล่าง)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileAvatar()
                Spacer(modifier = Modifier.height(16.dp))
                ProfileInfo()
            }
        } else {
            // แนวนอน / แท็บเล็ต: Row (รูปอยู่ซ้าย ข้อมูลอยู่ขวา)
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileAvatar(modifier = Modifier.weight(0.4f))
                Spacer(modifier = Modifier.width(16.dp))
                ProfileInfo(modifier = Modifier.weight(0.6f))
            }
        }
    }
}

@Composable
fun ProfileAvatar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFF9E9E9E)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "👤", style = MaterialTheme.typography.displayMedium)
        }
    }
}

@Composable
fun ProfileInfo(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(text = "ชื่อ: สมชาย ใจดี", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "อายุ: 25 ปี", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "อีเมล: somchai@example.com", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "เบอร์โทร: 081-234-5678", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "ที่อยู่: 123 ถ.สุขุมวิท แขวงคลองเตย เขตคลองเตย กรุงเทพมหานคร 10110",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun AdaptiveProfilePortraitPreview() {
    _517LabLearnAndroidTheme {
        AdaptiveProfileScreen()
    }
}

@Preview(showBackground = true, widthDp = 720)
@Composable
fun AdaptiveProfileLandscapePreview() {
    _517LabLearnAndroidTheme {
        AdaptiveProfileScreen()
    }
}
