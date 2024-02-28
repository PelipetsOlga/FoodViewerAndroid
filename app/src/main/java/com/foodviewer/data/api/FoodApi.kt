package com.foodviewer.data.api

import com.foodviewer.data.model.FoodItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {

    @GET("/dev/search")
    suspend fun getFood(
        @Query("kv") keyword: String
    ): List<FoodItemResponse>
}