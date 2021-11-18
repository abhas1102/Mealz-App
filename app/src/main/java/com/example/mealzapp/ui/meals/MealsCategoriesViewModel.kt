package com.example.mealzapp.ui.meals

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.MealsRepository
import com.example.model.response.MealResponse
import com.example.model.response.MealsCategoriesResponses
import kotlinx.coroutines.*
import kotlin.system.measureNanoTime

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()):ViewModel() {
   // private val mealsJob = Job()
    init {
       // val scope = CoroutineScope(mealsJob +Dispatchers.IO)
       Log.d("TAG_COROUTINES","we are about to launch a coroutine")
        viewModelScope.launch(Dispatchers.IO){
            Log.d("TAG_COROUTINES","we have launched the coroutine")
            val meals = getMeals()
            Log.d("TAG_COROUTINES","we have received the sync data")
            mealsState.value =meals
        }
       Log.d("TAG_COROUTINES","other work")
    }

    val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())
   /* override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    } */

    private suspend fun getMeals():List<MealResponse>{
      return  repository.getMeals().categories

    }
}