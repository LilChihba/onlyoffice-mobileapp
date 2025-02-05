package com.example.onlyoffice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.onlyoffice.ui.navigation.MainScreen
import com.example.onlyoffice.ui.theme.OnlyofficeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlyofficeTheme {
                MainScreen()
            }
        }
    }
}