package com.example.appbiblialeeer.ui.screens

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.appbiblialeeer.data.DailyReading
import com.example.appbiblialeeer.ui.components.ReferenciaItem
import com.example.appbiblialeeer.storage.loadDayProgress
import com.example.appbiblialeeer.storage.saveDayProgress
import com.example.appbiblialeeer.storage.saveDayAsCompleted

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleLecturaPantalla(
    lectura: DailyReading,
    dayIsCompleted: Boolean,
    onVolver: () -> Unit,
    onMarkDayAsCompleted: (Int) -> Unit
) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("PlanLectura", Context.MODE_PRIVATE)

    var completedReferences by remember {
        mutableStateOf(loadDayProgress(sharedPreferences, lectura.dia, lectura.referencias))
    }
    var selectedReference by remember { mutableStateOf<String?>(null) }

    val allCompleted = completedReferences.values.all { it }

    // Marcar el día como completado solo cuando todas las referencias lo estén
    LaunchedEffect(completedReferences) {
        val todoListo = completedReferences.values.all { it }
        if (todoListo && !dayIsCompleted) {
            onMarkDayAsCompleted(lectura.dia)
            saveDayAsCompleted(sharedPreferences, lectura.dia)
        }
    }

    BackHandler {
        if (selectedReference != null) {
            selectedReference = null
        } else {
            onVolver()
        }
    }

    if (selectedReference != null) {
        LecturaVersiculoPantalla(
            referencia = selectedReference!!,
            onVolver = { selectedReference = null },
            onCompletarLectura = {
                if (!completedReferences[selectedReference]!!) {
                    completedReferences = completedReferences.toMutableMap().apply {
                        this[selectedReference!!] = true
                    }
                    saveDayProgress(sharedPreferences, lectura.dia, completedReferences)
                }
            }
        )
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Lecturas del Día ${lectura.dia}",
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onVolver) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Progreso de lectura",
                    style = MaterialTheme.typography.titleMedium
                )

                LinearProgressIndicator(
                    progress = completedReferences.values.count { it }.toFloat() / completedReferences.size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .padding(vertical = 8.dp),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    color = if (allCompleted) Color(0xFF388E3C) else MaterialTheme.colorScheme.primary
                )

                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(lectura.referencias) { reference ->
                        ReferenciaItem(
                            referencia = reference,
                            completado = completedReferences[reference] == true,
                            onCompletar = {
                                // Desactivado: la lectura se marca desde LecturaVersiculoPantalla
                            },
                            onVerTexto = { selectedReference = reference }
                        )
                    }
                }
            }
        }
    }
}
