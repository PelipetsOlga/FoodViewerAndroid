package com.foodviewer.data

import com.foodviewer.data.api.ApiHelper
import com.foodviewer.domain.model.FoodItem
import com.foodviewer.domain.model.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val cache: SearchCache
) : FoodRepository {
    override fun getFood(keyword: String): Flow<List<FoodItem>> {
        val fromCache = cache.getFromCache(keyword)
        if (fromCache != null) {
            return flow { emit(fromCache) }
        }
        return apiHelper.getFood(keyword).onEach { cache.cache(keyword, it) }
    }
}