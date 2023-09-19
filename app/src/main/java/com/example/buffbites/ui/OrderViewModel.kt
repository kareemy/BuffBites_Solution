package com.example.buffbites.ui

import androidx.lifecycle.ViewModel
import com.example.buffbites.model.MenuItem
import com.example.buffbites.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OrderViewModel : ViewModel() {

    private val taxRate = 0.08

    private val _uiState = MutableStateFlow(OrderUiState(availableDeliveryTimes = deliveryTimeOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun updateVendor(restaurant: Restaurant) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedVendor = restaurant
            )
        }
    }

    fun updateMeal(menuItem: MenuItem) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedMeal = menuItem,
                orderSubtotal = menuItem.price,
                orderTax = menuItem.price * taxRate,
                orderTotalPrice = menuItem.price + menuItem.price * taxRate
            )
        }
    }

    fun updateDeliveryTime(deliveryTime: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedDeliveryTime = deliveryTime
            )
        }
    }

    fun resetOrder() {
        _uiState.value = OrderUiState(availableDeliveryTimes = deliveryTimeOptions())
    }

    private fun deliveryTimeOptions(): List<String> {
        val deliveryOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d h:mm aa", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MINUTE, 0)

        repeat(4) {
            calendar.add(Calendar.HOUR_OF_DAY, 1)
            deliveryOptions.add(formatter.format(calendar.time))
        }

        return deliveryOptions
    }
}