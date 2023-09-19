package com.example.buffbites

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.buffbites.ui.OrderViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.buffbites.data.Datasource
import com.example.buffbites.ui.ChooseDeliveryTimeScreen
import com.example.buffbites.ui.ChooseMenuScreen
import com.example.buffbites.ui.OrderSummaryScreen
import com.example.buffbites.ui.StartOrderScreen

// TODO: Screen enum
enum class BuffBitesScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Meal(title = R.string.choose_meal),
    Delivery(title = R.string.choose_delivery_time),
    Summary(title = R.string.order_summary)
}

// TODO: TopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuffBitesAppBar(
    currentScreen: BuffBitesScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun BuffBitesApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()

) {
    // TODO: Add navigation dependency to gradle script
    // TODO: Create Controller and initialization

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = BuffBitesScreen.valueOf(
        backStackEntry?.destination?.route ?: BuffBitesScreen.Start.name
    )

    Scaffold(
        topBar = {
            BuffBitesAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        // TODO: Navigation host
        NavHost(
            navController = navController,
            startDestination = BuffBitesScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = BuffBitesScreen.Start.name) {
                StartOrderScreen(
                    restaurantOptions = Datasource.restaurants,
                    onNextButtonClicked = {
                        viewModel.updateVendor(it)
                        navController.navigate(BuffBitesScreen.Meal.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
            composable(route = BuffBitesScreen.Meal.name) {
                ChooseMenuScreen(
                    options = uiState.selectedVendor?.menuItems ?: listOf(),
                    onSelectionChanged = { viewModel.updateMeal(it) },
                    onNextButtonClicked = { navController.navigate(BuffBitesScreen.Delivery.name) },
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
            composable(route = BuffBitesScreen.Delivery.name) {
                ChooseDeliveryTimeScreen(
                    subtotal = uiState.orderSubtotal,
                    options = uiState.availableDeliveryTimes,
                    onSelectionChanged = { viewModel.updateDeliveryTime(it) },
                    onNextButtonClicked = { navController.navigate(BuffBitesScreen.Summary.name)},
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
            composable(route = BuffBitesScreen.Summary.name) {
                val context = LocalContext.current
                OrderSummaryScreen(
                    orderUiState = uiState,
                    onSubmitButtonClicked = {
                        // Standard credit - When user clicks submit, cancel and go back to beginning
                        //cancelOrderAndNavigateToStart(viewModel, navController)
                        // Extra credit - ShareScreen
                        subject: String, summary: String ->
                        shareOrder(context, subject = subject, summary = summary)
                    },
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    navController.popBackStack(BuffBitesScreen.Start.name, inclusive = false)
    viewModel.resetOrder()
}

private fun shareOrder(context: Context, subject: String, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_buffbites_order)
        )
    )
}