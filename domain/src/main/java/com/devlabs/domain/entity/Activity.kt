package com.devlabs.domain.entity

data class Activity(
    val activity: String,
    val type: String,
    val participants: Int,
    val price: Float,
    val link: String,
    val key: String,
    val accessibility: Int
)