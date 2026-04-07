package com.example.a520lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a520lablearnandroid.ui.theme._517LabLearnAndroidTheme

class Mission3DonutChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _517LabLearnAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DonutChartScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DonutChartScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Donut Chart",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        DonutChart(
            proportions = listOf(30f, 40f, 30f),
            colors = listOf(Color(0xFF2196F3), Color(0xFF4CAF50), Color(0xFFFF9800)),
            modifier = Modifier.size(250.dp)
        )
    }
}

@Composable
fun DonutChart(
    proportions: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier,
    strokeWidth: Float = 60f
) {
    // Animation: วาดจาก 0 ไปจนถึง 360 องศา
    val animatedSweep = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedSweep.animateTo(
            targetValue = 360f,
            animationSpec = tween(durationMillis = 1500)
        )
    }

    Canvas(modifier = modifier) {
        val total = proportions.sum()
        val diameter = size.minDimension - strokeWidth
        val topLeft = Offset(
            x = (size.width - diameter) / 2f,
            y = (size.height - diameter) / 2f
        )
        val arcSize = Size(diameter, diameter)

        var startAngle = -90f
        proportions.forEachIndexed { index, proportion ->
            val fullSweep = (proportion / total) * 360f
            // คำนวณ sweep ที่จะวาดตาม animation progress
            val animatedProgress = animatedSweep.value / 360f
            val drawnSoFar = proportions.take(index).sum() / total * 360f
            val sweep = if (drawnSoFar >= animatedSweep.value) {
                0f
            } else {
                minOf(fullSweep, animatedSweep.value - drawnSoFar)
            }

            if (sweep > 0f) {
                drawArc(
                    color = colors[index % colors.size],
                    startAngle = startAngle,
                    sweepAngle = sweep,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                )
            }
            startAngle += fullSweep
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DonutChartScreenPreview() {
    _517LabLearnAndroidTheme {
        DonutChartScreen()
    }
}
