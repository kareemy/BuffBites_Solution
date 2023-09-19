package com.example.buffbites.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buffbites.R
import com.example.buffbites.data.Datasource
import com.example.buffbites.ui.theme.BuffBitesTheme
import java.text.NumberFormat

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    onSubmitButtonClicked: (String, String) -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Format all prices into strings with dollar sign and 2 decimal places (e.g. $4.99)
    val formattedSubTotal = NumberFormat.getCurrencyInstance().format(orderUiState.orderSubtotal)
    val formattedTax = NumberFormat.getCurrencyInstance().format(orderUiState.orderTax)
    val formattedTotal = NumberFormat.getCurrencyInstance().format(orderUiState.orderTotalPrice)

    // Extract vendor name from orderUiState or use an empty string if null
    val vendorName = orderUiState.selectedVendor?.name?.let { stringResource(it) } ?: ""

    // Order Subject and Summary text to send to another app for extra credit
    val orderSubject = stringResource(R.string.new_buffbites_order)
    val orderSummary = stringResource(
        R.string.order_details,
        vendorName,
        orderUiState.selectedMeal?.name ?: "",
        orderUiState.selectedDeliveryTime,
        formattedTotal
    )

    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),

        ) {
        Text(
            text = stringResource(R.string.order_summary_detail, vendorName),
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(orderUiState.selectedMeal?.name ?: "")
            Text(formattedSubTotal)
        }
        Text(
            text = stringResource(R.string.delivery_time, orderUiState.selectedDeliveryTime)
        )
        Divider(
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = stringResource(R.string.subtotal, formattedSubTotal),
            modifier = Modifier.align(Alignment.End)
        )
        Text(
            text = stringResource(R.string.tax, formattedTax),
            modifier = Modifier.align(Alignment.End)
        )
        Text(
            text = stringResource(R.string.total, formattedTotal),
            modifier = Modifier.align(Alignment.End),
            fontWeight = FontWeight.Bold
        )

        // Align buttons to bottom of screen with a spacer
        //Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = { onSubmitButtonClicked(orderSubject, orderSummary) }
            ) {
                Text(stringResource(R.string.submit))
            }
        }
    }
}

@Preview
@Composable
fun OrderSummaryScreenPreview() {
    BuffBitesTheme {
        OrderSummaryScreen(
            orderUiState = OrderUiState(
                selectedVendor = Datasource.restaurants[0],
                selectedMeal = Datasource.restaurants[0].menuItems[0],
                orderSubtotal = Datasource.restaurants[0].menuItems[0].price,
                orderTax = Datasource.restaurants[0].menuItems[0].price * 0.08,
                orderTotalPrice = Datasource.restaurants[0].menuItems[0].price + Datasource.restaurants[0].menuItems[0].price * 0.08,
                selectedDeliveryTime = "Sat Sep 24 7:00 PM"
            ),
            onSubmitButtonClicked = { subject: String, summary: String -> },
            onCancelButtonClicked = {},
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        )
    }
}