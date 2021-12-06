package com.mking11.community_project.common.utils

import java.util.HashMap

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