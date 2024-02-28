package com.foodviewer.data

import com.foodviewer.data.model.FoodItemResponse
import com.foodviewer.domain.model.FoodItem

fun FoodItemResponse.toDomain(): FoodItem {
    return FoodItem(name = name)
}
