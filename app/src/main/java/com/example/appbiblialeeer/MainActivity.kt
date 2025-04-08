package com.example.appbiblialeeer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.appbiblialeeer.ui.theme.BibliaAppTheme
import com.example.appbiblialeeer.ui.AppBiblialectura

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BibliaAppTheme {
                AppBiblialectura()
            }
        }
    }
}
