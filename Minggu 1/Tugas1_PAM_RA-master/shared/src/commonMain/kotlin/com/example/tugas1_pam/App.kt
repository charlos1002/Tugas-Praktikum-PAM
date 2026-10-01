package com.example.tugas1_pam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White
        ) {

            val rawGreeting = remember { Greeting().greet() }
            val platformName = rawGreeting
                .removePrefix("Hello, ")
                .removeSuffix("!")

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Halo, Roberto Charlos Sagala!")
                Spacer(modifier = Modifier.height(4.dp))
                Text("NIM: 123140113")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Platform: $platformName")
            }
        }
    }
}