package com.example.appbiblialeeer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text

@Composable
fun DiaItem(
    dia: Int,
    completado: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (completado) Color(0xFF0D47A1) else MaterialTheme.colorScheme.surface // Azul oscuro para completados
    val textColor = if (completado) Color(0xFFBBDEFB) else MaterialTheme.colorScheme.onSurface // Azul claro para texto completado

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick)
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "Día $dia",
            color = textColor,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
