package com.example.news

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.news.presentation.onboarding.OnBoarding
import com.example.styleage.ui.theme.NewsAppTheme
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window,false)
        installSplashScreen()
        setContent {
            NewsAppTheme{
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
                    OnBoarding()
                }
            }
        }
    }
}