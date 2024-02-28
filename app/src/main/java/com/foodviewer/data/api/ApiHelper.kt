package com.foodviewer.data.api

import com.foodviewer.domain.model.FoodItem
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    fun getFood(keyword: String): Flow<List<FoodItem>>
}