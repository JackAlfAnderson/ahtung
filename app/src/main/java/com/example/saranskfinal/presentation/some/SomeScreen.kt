package com.example.saranskfinal.presentation.some

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun SimpleSpeechToTextWithIndicator() {
    val text = remember { mutableStateOf("") }
    val isListening = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val speechLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        isListening.value = false
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let {
                text.value = it[0]
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        if (isListening.value) {
            Text("Идет запись...", modifier = Modifier.padding(bottom = 8.dp))
        }

        TextField(
            value = text.value,
            onValueChange = { text.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                isListening.value = true
                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                }
                speechLauncher.launch(intent)
            }
        ) {
            Text(if (isListening.value) "Распознавание..." else "Начать запись")
        }
    }
}