package com.example.model.api

import com.example.model.response.MealsCategoriesResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//should be a class that returns an actual retrofit response
class MealsWebService {
    private lateinit var api:MealsApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create()) //converting the response into json object
            .build()

        api = retrofit.create(MealsApi::class.java)

    }

    suspend fun getMeals():MealsCategoriesResponses{
            return api.getMeals()
    }

    interface MealsApi{
        @GET("categories.php")
        suspend fun getMeals():MealsCategoriesResponses
    }
}