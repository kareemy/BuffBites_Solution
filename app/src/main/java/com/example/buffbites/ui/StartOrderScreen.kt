package com.example.buffbites.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buffbites.R
import com.example.buffbites.data.Datasource
import com.example.buffbites.model.Restaurant
import com.example.buffbites.ui.theme.BuffBitesTheme

@Composable
fun StartOrderScreen(
    restaurantOptions: List<Restaurant>,
    onNextButtonClicked: (Restaurant) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(R.drawable.buffalo),
                contentDescription = null,
                modifier = Modifier.width(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.select_vendor),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Row() {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                restaurantOptions.forEach {
                    Button(
                        onClick = { onNextButtonClicked(it) },
                        modifier = Modifier.widthIn(min = 250.dp),
                    ) {
                        Text(stringResource(it.name))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun StartOrderPreview(){
    BuffBitesTheme {
        StartOrderScreen(
            restaurantOptions = Datasource.restaurants,
            onNextButtonClicked = { },
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        )
    }
}