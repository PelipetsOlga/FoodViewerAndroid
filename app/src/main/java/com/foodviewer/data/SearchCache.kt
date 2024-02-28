package com.foodviewer.data

import com.foodviewer.domain.model.FoodItem
import javax.inject.Inject

interface SearchCache {
    fun cache(keyword: String, values: List<FoodItem>)
    fun getFromCache(keyword: String): List<FoodItem>?
}

class SearchCacheImpl @Inject constructor() : SearchCache{
    private val cache = mutableMapOf<String, List<FoodItem>>()
    override fun cache(keyword: String, values: List<FoodItem>) {
       cache[keyword] = values
    }

    override fun getFromCache(keyword: String): List<FoodItem>? {
        return cache[keyword]
    }
}
