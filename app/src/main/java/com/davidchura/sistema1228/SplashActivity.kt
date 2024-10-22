package com.davidchura.sistema1228

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val startAnimation = remember { mutableStateOf(false) }
            val offsetY = animateDpAsState(
                targetValue = if (startAnimation.value) 0.dp else 1500.dp,
                animationSpec = tween(durationMillis = 1000)
            )
            val alpha = animateFloatAsState(
                targetValue = if (startAnimation.value) 1f else 0f,
                animationSpec = tween(durationMillis = 4000)
            )
            val scale = animateFloatAsState(
                targetValue = if (startAnimation.value) 1.5f else 3f,
                animationSpec = tween(durationMillis = 2500)
            )
            Sistema1228Theme {
                LaunchedEffect(key1 = true) {
                    startAnimation.value = true
                    delay(4000)
                    lifecycleScope.launch {
                        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                    }
                }
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.icono),
                        contentDescription = null,
                        modifier = Modifier.offset(y = offsetY.value)
                            .graphicsLayer(
                                alpha = alpha.value,
                                scaleX = scale.value,
                                scaleY = scale.value
                            )
                    )
                }
            }
        }
    }
}
