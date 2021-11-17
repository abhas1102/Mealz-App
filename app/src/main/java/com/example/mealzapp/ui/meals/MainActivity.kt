package com.example.mealzapp.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealzapp.ui.theme.MealzAppTheme

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
    val meals = viewModel.getMeals()
    Text(text = meals.toString())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        MealsCategoriesScreen()
    }
}