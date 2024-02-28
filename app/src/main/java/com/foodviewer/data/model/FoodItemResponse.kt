package com.foodviewer.data.model

data class FoodItemResponse(
    val id: Int,
    val brand: String,
    val name: String,
    val calories: Int,
    val portion: Int
)
