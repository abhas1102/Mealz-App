package com.example.mealzapp.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealzapp.ui.theme.MealzAppTheme
import com.example.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                // A surface container using the 'background' color from the theme
                    MealsCategoriesScreen()
                }
            }
        }
    }


@Composable
fun MealsCategoriesScreen() {
    val viewModel:MealsCategoriesViewModel = viewModel() // special syntax to instantiate viewmodel in composable screen, viewmodel will not be reinstantiated all over the time when composable is rebuild
    val meals = viewModel.mealsState.value
   /* val rememberedMeals:MutableState<List<MealResponse>> = remember {
        mutableStateOf(emptyList<MealResponse>())
    } */
    //val coroutineScope = rememberCoroutineScope()
    //Launched Effect is called in composables to call anything once
   /* LaunchedEffect(key1="GET_MEALS"){

        coroutineScope.launch(Dispatchers.IO) {
            val meals = viewModel.getMeals()
            rememberedMeals.value = meals
        }
    } */
    LazyColumn{
        items(meals){meals->
            Text(text = meals.name)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        MealsCategoriesScreen()
    }
}