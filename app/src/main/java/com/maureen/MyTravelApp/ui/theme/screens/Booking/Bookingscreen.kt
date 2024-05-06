package com.maureen.MyTravelApp.ui.theme.screens.Booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.time.LocalDate

data class BookingDetails(
    val checkInDate: LocalDate?,
    val checkOutDate: LocalDate?,
    val numGuests: Int,
    val includeBreakfast: Boolean,
    val includeAirportTransfer: Boolean,
    val cardNumber: String,
    val cardExpiry: String,
    val cardCVC: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    onBook: NavHostController
) {
    var checkInDate by remember { mutableStateOf<LocalDate?>(null) }
    var checkOutDate by remember { mutableStateOf<LocalDate?>(null) }
    var numGuests by remember { mutableStateOf(1) }
    var includeBreakfast by remember { mutableStateOf(false) }
    var includeAirportTransfer by remember { mutableStateOf(false) }
    var cardNumber by remember { mutableStateOf(TextFieldValue("")) }
    var cardExpiry by remember { mutableStateOf(TextFieldValue("")) }
    var cardCVC by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking") }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Date selection (simplified)
                DatePicker(
                    label = "Check-in Date",
                    selectedDate = checkInDate,
                    onDateSelected = { checkInDate = it }
                )

                DatePicker(
                    label = "Check-out Date",
                    selectedDate = checkOutDate,
                    onDateSelected = { checkOutDate = it }
                )

                // Number of Guests
                NumberPicker(
                    label = "Number of Guests",
                    value = numGuests,
                    onValueChange = { numGuests = it }
                )

                // Additional Services
                CheckboxWithLabel(
                    label = "Include Breakfast",
                    isChecked = includeBreakfast,
                    onCheckedChange = { includeBreakfast = it }
                )

                CheckboxWithLabel(
                    label = "Include Airport Transfer",
                    isChecked = includeAirportTransfer,
                    onCheckedChange = { includeAirportTransfer = it }
                )

                // Payment Information
                TextField(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    label = { Text("Card Number") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = cardExpiry,
                        onValueChange = { cardExpiry = it },
                        label = { Text("Expiry (MM/YY)") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )

                    TextField(
                        value = cardCVC,
                        onValueChange = { cardCVC = it },
                        label = { Text("CVC") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Book Button
                Button(
                    onClick = {
                        val bookingDetails = BookingDetails(
                            checkInDate = checkInDate,
                            checkOutDate = checkOutDate,
                            numGuests = numGuests,
                            includeBreakfast = includeBreakfast,
                            includeAirportTransfer = includeAirportTransfer,
                            cardNumber = cardNumber.text,
                            cardExpiry = cardExpiry.text,
                            cardCVC = cardCVC.text
                        )
//                        onBook(bookingDetails)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Book Now")
                }
            }
        }
    )
}

@Composable
fun DatePicker(
    label: String,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate?) -> Unit
) {
    // Date Picker Implementation
}

@Composable
fun NumberPicker(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(8.dp)
        )

        Row {
            IconButton(onClick = { if (value > 1) onValueChange(value - 1) }) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Decrease"
                )
            }

            Text(
                text = value.toString(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            IconButton(onClick = { onValueChange(value + 1) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase"
                )
            }
        }
    }
}

@Composable
fun CheckboxWithLabel(
    label: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )

        Text(
            text = label,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}