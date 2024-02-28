package com.foodviewer.domain.model

import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getFood(keyword: String): Flow<List<FoodItem>>
}