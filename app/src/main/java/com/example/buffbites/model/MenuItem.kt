package com.example.buffbites.model

import androidx.annotation.StringRes

data class MenuItem(
    val name: String,
    val description: String,
    val price: Double
)

data class Restaurant (
    @StringRes val name: Int,
    val menuItems: List<MenuItem>
)