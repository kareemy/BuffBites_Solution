package com.example.buffbites.ui

import com.example.buffbites.model.MenuItem
import com.example.buffbites.model.Restaurant

data class OrderUiState(
    val selectedVendor: Restaurant? = null,
    val selectedMeal: MenuItem? = null,
    val orderSubtotal: Double = 0.0,
    val orderTax: Double = 0.0,
    val orderTotalPrice: Double = 0.0,
    /** Selected time for delivery (such as "7:00 PM") */
    val selectedDeliveryTime: String = "",
    /** Available delivery times for the order */
    val availableDeliveryTimes: List<String> = listOf()
)