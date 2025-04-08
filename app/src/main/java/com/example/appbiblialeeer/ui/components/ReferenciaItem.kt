package com.example.appbiblialeeer.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ReferenciaItem(
    referencia: String,
    completado: Boolean,
    onCompletar: () -> Unit, // Ya no se usará aquí, pero lo dejamos para que se use desde la pantalla
    onVerTexto: () -> Unit
) {
    val containerColor = if (completado) Color(0xFFBBDEFB) else Color(0xFFF5F5F5)
    val iconTint = if (completado) Color(0xFF1E88E5) else Color(0xFFBDBDBD)
    val textColor = if (completado) Color(0xFF1E88E5) else Color.Black

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                onVerTexto() // Solo navega al texto, sin marcar como completado aquí
            },
        colors = CardDefaults.outlinedCardColors(containerColor = containerColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Referencia completada",
                tint = iconTint
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = referencia,
                color = textColor,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
