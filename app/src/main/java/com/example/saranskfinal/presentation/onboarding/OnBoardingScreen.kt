package com.example.saranskfinal.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.saranskfinal.R
import com.example.saranskfinal.data.internetConnection.hasInternetConnection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    val onBoardingList = listOf(
        OnBoardingItem(
            R.drawable.onboardingone,
            "Confidence in your words",
            "With conversation-based learning, you'll be talking from lesson one",
            "Next",
        ), OnBoardingItem(
            R.drawable.onboardingone,
            "Take your time to learn",
            "Develop a habit of learning and make it a part of your daily routine",
            "more",
        ), OnBoardingItem(
            R.drawable.onboardingone,
            "The lessons you need to learn",
            "Using a variety of learning styles to learn and retain",
            "choose a language",
        )
    )

    val pagerState = rememberPagerState { onBoardingList.size }
    val scope = rememberCoroutineScope()
    val isConnected by rememberConnectivityState()

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                pagerState
            ) { page ->
                OnBoardingPage(onBoardingList[page])
            }
        }
        Box (
            Modifier
                .fillMaxSize()
                .padding(bottom = 322.dp),
            contentAlignment = Alignment.BottomCenter
        ){
            Row {
                repeat(onBoardingList.size){
                    Spacer(Modifier.width(4.dp))
                    Card(
                        shape = RoundedCornerShape(100),
                        colors = CardDefaults.cardColors(
                            containerColor = if(pagerState.currentPage == it) Color.Red else Color.LightGray
                        ),
                        modifier = Modifier.size(8.dp)
                    ) {

                    }
                    Spacer(Modifier.width(4.dp))
                }
            }

        }
        Box(
            Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B7BFE)
                ),
                modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 24.dp),
            ) {
                Text(onBoardingList[pagerState.currentPage].buttonText, fontSize = 20.sp)
            }
        }
        Box (
            modifier = Modifier.fillMaxSize().padding(bottom = 64.dp),
            contentAlignment = Alignment.BottomCenter
        ){
            Text("Skip onboarding", color = MaterialTheme.colorScheme.onSurface)
        }
        if (!isConnected){
            Dialog(
                onDismissRequest = {

                }
            ) {
                Box (Modifier.fillMaxSize().padding(20.dp).background(Color.White),
                    contentAlignment = Alignment.Center){
                    Text("Нет интернета")
                }
            }
        }

    }
}

@Preview
@Composable
private fun OnBoardingPreview() {
    OnBoardingScreen()
}

@Composable
fun OnBoardingPage(onBoardingItem: OnBoardingItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(onBoardingItem.image), null, tint = Color.Unspecified
        )
        Spacer(Modifier.height(110.dp))
        Text(onBoardingItem.text, color = MaterialTheme.colorScheme.onSurface, fontSize = 22.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(Modifier.height(8.dp))
        Text(onBoardingItem.subtext, color = Color.Gray, modifier = Modifier.padding(horizontal = 30.dp), textAlign = TextAlign.Center)
    }
}

data class OnBoardingItem(
    val image: Int, val text: String, val subtext: String, val buttonText: String
)

@Composable
fun rememberConnectivityState(): State<Boolean> {
    val context = LocalContext.current

    return produceState(initialValue = context.hasInternetConnection()) {
        while (true) {
            value = context.hasInternetConnection()
            delay(2000) // Проверяем каждые 2 секунды
        }
    }
}