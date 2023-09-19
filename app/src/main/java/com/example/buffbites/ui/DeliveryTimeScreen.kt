package com.example.buffbites.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buffbites.R
import com.example.buffbites.ui.theme.BuffBitesTheme
import java.text.NumberFormat

@Composable
fun ChooseDeliveryTimeScreen(
    subtotal: Double,
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {},
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(
                text = stringResource(R.string.select_a_delivery_time)
            )
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
            Divider(
                thickness = 1.dp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            val formattedSubTotal = NumberFormat.getCurrencyInstance().format(subtotal)
            Text(
                text = stringResource(R.string.subtotal, formattedSubTotal),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp, bottom = 16.dp)
            )
        }
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
                // the button is enabed when the user makes a selection
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}

@Preview
@Composable
fun ChooseDeliveryTimePreview() {
    BuffBitesTheme {
        ChooseDeliveryTimeScreen(
            subtotal = 299.99,
            options = listOf(
                "Mon Sep 18 6:00 PM",
                "Mon Sep 18 7:00 PM",
                "Mon Sep 18 8:00 PM",
                "Mon Sep 18 9:00 PM"
            ),
            onNextButtonClicked = {},
            onCancelButtonClicked = {},
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
        )
    }
}