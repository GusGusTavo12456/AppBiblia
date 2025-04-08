package com.example.appbiblialeeer.storage

import android.content.SharedPreferences

fun saveDayProgress(sharedPreferences: SharedPreferences, day: Int, progress: Map<String, Boolean>) {
    val editor = sharedPreferences.edit()
    progress.forEach { (reference, isCompleted) ->
        editor.putBoolean("Day_${day}_$reference", isCompleted)
    }
    editor.apply()
}

fun loadDayProgress(sharedPreferences: SharedPreferences, day: Int, references: List<String>): Map<String, Boolean> {
    return references.associateWith { reference ->
        sharedPreferences.getBoolean("Day_${day}_$reference", false)
    }
}

fun saveDayAsCompleted(sharedPreferences: SharedPreferences, dia: Int) {
    val editor = sharedPreferences.edit()
    editor.putBoolean("Day_$dia", true)
    editor.apply()
}

fun loadCompletedDays(sharedPreferences: SharedPreferences): MutableMap<Int, Boolean> {
    val completedDays = mutableMapOf<Int, Boolean>()
    for (i in 1..31) {
        completedDays[i] = sharedPreferences.getBoolean("Day_$i", false)
    }
    return completedDays
}
