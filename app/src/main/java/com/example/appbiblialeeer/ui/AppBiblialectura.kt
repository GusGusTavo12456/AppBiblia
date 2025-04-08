package com.example.appbiblialeeer.ui

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.appbiblialeeer.*
import com.example.appbiblialeeer.data.biblePlan
import com.example.appbiblialeeer.storage.loadCompletedDays
import com.example.appbiblialeeer.storage.saveDayAsCompleted
import com.example.appbiblialeeer.ui.screens.DetalleLecturaPantalla
import com.example.appbiblialeeer.ui.screens.ListaDiasPantalla

@Composable
fun AppBiblialectura() {
    val sharedPreferences = LocalContext.current.getSharedPreferences("PlanLectura", Context.MODE_PRIVATE)
    val completedDays = remember { mutableStateOf(loadCompletedDays(sharedPreferences)) }
    var selectedDay by remember { mutableStateOf<Int?>(null) }
    val listState = rememberLazyListState()

    BackHandler(enabled = (selectedDay != null)) {
        selectedDay = null
    }

    if (selectedDay == null) {
        ListaDiasPantalla(
            biblePlan,
            completedDays.value,
            listState = listState,
            onDiaSeleccionado = { day -> selectedDay = day }
        )
    } else {
        DetalleLecturaPantalla(
            lectura = biblePlan[selectedDay!! - 1],
            dayIsCompleted = completedDays.value[selectedDay!!] == true,
            onVolver = { selectedDay = null },
            onMarkDayAsCompleted = { day ->
                completedDays.value = completedDays.value.toMutableMap().apply { this[day] = true }
                saveDayAsCompleted(sharedPreferences, day)
            }
        )
    }
}
