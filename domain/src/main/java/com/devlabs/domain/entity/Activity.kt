package com.devlabs.domain.entity

import java.util.*

data class Activity(
    val activity: String,
    val type: String,
    val participants: Int,
    val price: Float,
    val link: String,
    val key: String,
    val accessibility: Float,
    var progressStatus: ProgressStatus,
    val startDate: Date,
    val endDate: Date,
    val timeSpent: Int
)