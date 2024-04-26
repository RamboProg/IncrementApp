package com.example.incrementten

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.counter.ui.theme.CounterTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Counter()
                }
            }
        }
    }
}
@Composable
fun Counter() {
    var count by remember { mutableStateOf(1) }
    var showAlert by remember { mutableStateOf(false) }

    if (count >= 10) {
        showAlert = true
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = {
                showAlert = false
            },
            title = { Text("Alert") },
            text = { Text("You have reached 10") },
            confirmButton = {
                Button(
                    onClick = {
                        count = 1
                        showAlert = false
                    }) {
                    Text("Reset")
                }
            }
        )
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = count.toString(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (count < 10) {
                    count++
                }
            }) {
                Text("Increment")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    CounterTheme {
        Counter()
    }
}