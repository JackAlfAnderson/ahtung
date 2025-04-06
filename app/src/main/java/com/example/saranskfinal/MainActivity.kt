package com.example.saranskfinal

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saranskfinal.data.localizationHelper.LocalizationHelper
import com.example.saranskfinal.data.localizationHelper.StringLocalization
import com.example.saranskfinal.presentation.onboarding.OnBoardingScreen
import com.example.saranskfinal.presentation.some.SimpleSpeechToTextWithIndicator
import com.example.saranskfinal.presentation.splash.SplashScreen
import com.example.saranskfinal.ui.theme.SaranskFinalTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        createNotificationChannel()

    }
    val requestPermissionLauncher by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {

            }
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        val name = "some"
        val descriptionText = "description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system.

        notificationManager.createNotificationChannel(channel)
    }
    val notificationManager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
                        SimpleSpeechToTextWithIndicator()
                    }
                    composable<OnBoarding> {
                        OnBoardingScreen()
                        LocalizationHelper.setLanguage("en")
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        var builder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.splashicon)
            .setContentTitle("Lelele")
            .setContentText("Obratno zaidy durachok")
        notificationManager.notify(1, builder.build())
    }
}

@Serializable
object Splash

@Serializable
object OnBoarding