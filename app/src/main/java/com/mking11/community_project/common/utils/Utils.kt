package com.mking11.community_project.common.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.toList(): List<String> {
    return try {
        this.replace("[", "").replace("]", "").split(", ")
    } catch (e: Exception) {
        listOf()
    }

}


fun String.toMap(): HashMap<String, String> {
    val map: HashMap<String, String> = hashMapOf()
    try {
        this.replace("{", "").replace("}", "").split(", ").associateTo(map) { ls ->
            val (left, right) = ls.split("=")
            left to right
        }
    } catch (e: Exception) {
    }

    return map

}

private val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)

private val DateFormatter: SimpleDateFormat = SimpleDateFormat("yyMMddHHmmssZ", Locale.ENGLISH)
private val timeFormat = SimpleDateFormat("mm:ss", Locale.getDefault())

fun getCurrentDate(): String = DateFormatter.format(Calendar.getInstance().time)

fun getDate(orderDate: String?): String? {
    // date formatter
    return try {
        orderDate?.dropLast(15)
    } catch (e: Exception) {
        null
    }
}