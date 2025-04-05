package com.example.saranskfinal.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.saranskfinal.OnBoarding
import com.example.saranskfinal.R
import com.example.saranskfinal.ui.theme.myFontFamily
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(OnBoarding)
    }

    Box (
        Modifier.fillMaxSize().background(Color(0xFF410FA3)),
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(R.drawable.splashicon),
                null,
                tint = Color.Unspecified,
                modifier = Modifier.size(164.dp)
            )
            Text("LanguageApp", fontSize = 36.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }

}

@Preview
@Composable
private fun SplashPreview() {
    SplashScreen(rememberNavController())
}