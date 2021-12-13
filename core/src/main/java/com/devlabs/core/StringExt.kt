package com.devlabs.core

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDate(input: Date): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
    return formatter.format(parser.parse(input.toString()))
}