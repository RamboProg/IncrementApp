package com.example.incrementten

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.incrementten.ui.theme.IncrementTenTheme
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IncrementTenTheme {
                val number = remember { mutableStateOf(0) }
                val showCongratsDialog = remember { mutableStateOf(false) }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = number.value.toString(),
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Button(
                            onClick = {
                                number.value++
                                if (number.value == 10) {
                                    showCongratsDialog.value = true
                                }
                            }
                        ) {
                            Text(text = "Increment")
                        }
                    }
                    if (showCongratsDialog.value) {
                        CongratsDialog(onClose = {
                            // Reset number before hiding dialog
                            number.value = 0
                            showCongratsDialog.value = false
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun CongratsDialog(onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = onClose,
        title = { Text(text = "Congratulations!") },
        text = { Text(text = "You have reached the number 10!") },
        confirmButton = {
            Button(onClick = onClose) {
                Text(text = "OK")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IncrementTenTheme {
    }
}