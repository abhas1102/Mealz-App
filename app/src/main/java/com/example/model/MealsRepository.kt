package com.example.model

import com.example.model.api.MealsWebService
import com.example.model.response.MealsCategoriesResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService= MealsWebService()) {
    fun getMeals(successCallBack:(response:MealsCategoriesResponses?)->Unit)  {
         //   return webService.getMeals().execute().body() //execute will block the thread main thread till we get response

        return webService.getMeals().enqueue(object :Callback<MealsCategoriesResponses>{
            override fun onResponse(
                call: Call<MealsCategoriesResponses>,
                response: Response<MealsCategoriesResponses>
            ) {

                if (response.isSuccessful)
                    successCallBack(response.body())
            }

            override fun onFailure(call: Call<MealsCategoriesResponses>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }
}