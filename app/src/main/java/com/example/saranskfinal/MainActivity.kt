package com.example.saranskfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saranskfinal.data.localizationHelper.LocalizationHelper
import com.example.saranskfinal.data.localizationHelper.StringLocalization
import com.example.saranskfinal.presentation.onboarding.OnBoardingScreen
import com.example.saranskfinal.presentation.splash.SplashScreen
import com.example.saranskfinal.ui.theme.SaranskFinalTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaranskFinalTheme {
                val navController = rememberNavController()

                NavHost(
                    navController,
                    startDestination = Splash
                ){
                    composable<Splash> {
                        SplashScreen(navController)
                        Text(StringLocalization(R.string.app_name))
                    }
                    composable<OnBoarding> {
                        OnBoardingScreen()
                        LocalizationHelper.setLanguage("en")
                    }
                }
            }
        }
    }
}

@Serializable
object Splash

@Serializable
object OnBoarding