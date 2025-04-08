package com.example.appbiblialeeer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appbiblialeeer.data.DailyReading
import com.example.appbiblialeeer.ui.components.DiaItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDiasPantalla(
    biblePlan: List<DailyReading>,
    completedDays: Map<Int, Boolean>,
    listState: LazyListState,
    onDiaSeleccionado: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Plan de Lectura Bíblica - Abril",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(biblePlan) { dayReading ->
                val isCompleted = completedDays[dayReading.dia] ?: false
                DiaItem(
                    dia = dayReading.dia,
                    completado = isCompleted,
                    onClick = { onDiaSeleccionado(dayReading.dia) }
                )
            }
        }
    }
}
