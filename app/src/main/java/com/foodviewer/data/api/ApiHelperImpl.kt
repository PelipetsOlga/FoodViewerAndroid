package com.foodviewer.data.api

import com.foodviewer.data.toDomain
import com.foodviewer.domain.model.FoodItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val api: FoodApi) : ApiHelper {

    override fun getFood(keyword: String): Flow<List<FoodItem>> {
        return flow { emit(api.getFood(keyword)) }
            .onEach { delay(1000) }// to demonstrate loading
            .flowOn(Dispatchers.IO)
            .map { list -> list.map { it.toDomain() } }
    }
}