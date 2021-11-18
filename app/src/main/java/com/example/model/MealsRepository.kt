package com.example.model

import com.example.model.api.MealsWebService
import com.example.model.response.MealsCategoriesResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService= MealsWebService()) {
    suspend fun getMeals():MealsCategoriesResponses  {
         //   return webService.getMeals().execute().body() //execute will block the thread main thread till we get response
        return webService.getMeals()




    }
}