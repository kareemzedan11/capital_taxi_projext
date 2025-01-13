package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import com.example.capital_taxi.R

data class VehicleOption(
    val name: String,
    val price: Double,
    val imageRes: Int
)

val vehicleOptions = listOf(
    VehicleOption("Standard", 8.50, R.drawable.uber),
    VehicleOption("Comfort", 10.50, R.drawable.uber),
    VehicleOption("Luxury", 13.50, R.drawable.uber)
)

