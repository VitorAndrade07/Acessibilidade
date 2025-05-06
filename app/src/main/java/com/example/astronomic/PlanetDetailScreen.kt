package com.example.astronomic

import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailScreen(planetName: String) {
    val context = LocalContext.current
    var tts: TextToSpeech? = remember { null }
    val description = when (planetName) {
        "Marte" -> "Marte é o planeta vermelho, com solo rico em ferro."
        "Júpiter" -> "Júpiter é o maior planeta do sistema solar e possui uma grande mancha vermelha."
        "Saturno" -> "Saturno é conhecido por seus anéis compostos de gelo e poeira."
        else -> "Informação não disponível."
    }

    // Inicia o TTS
    LaunchedEffect(Unit) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("pt", "BR")
                tts?.speak(description, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    // Libera o TTS quando a tela for descartada
    DisposableEffect(Unit) {
        onDispose {
            tts?.shutdown()
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(planetName) }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(text = description, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                tts?.speak(description, TextToSpeech.QUEUE_FLUSH, null, null)
            }) {
                Icon(Icons.Default.VolumeUp, contentDescription = "Ouvir descrição")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Ouvir descrição")
            }
        }
    }
}
