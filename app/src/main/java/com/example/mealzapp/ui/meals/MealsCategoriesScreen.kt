package com.example.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.mealzapp.ui.theme.MealzAppTheme
import com.example.model.response.MealResponse
import javax.security.auth.callback.Callback

@Composable
fun MealsCategoriesScreen(navigationCallback: (String) -> Unit) {
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
    LazyColumn(contentPadding = PaddingValues(16.dp)){
        items(meals){meals->
            MealCategory(meals, navigationCallback)
        }
    }

}
@Composable
fun MealCategory(meal:MealResponse,navigationCallback: (String) -> Unit){
    var isExpanded by remember{ mutableStateOf(false)}
    Card(shape = RoundedCornerShape(10.dp), elevation = 2.dp,modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp).clickable { navigationCallback(meal.id) }) {
        Row(modifier = Modifier.animateContentSize()){
            //Image
            Image(
                painter = rememberImagePainter(meal.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )

            Column(modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth(0.8f)
                .padding(16.dp)){
                Text(text = meal.name,style = MaterialTheme.typography.h6)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = meal.description,textAlign = TextAlign.Start, style = MaterialTheme.typography.subtitle2,
                        overflow = TextOverflow.Ellipsis, maxLines = if (isExpanded) 10 else 4)
                    
                }


            }
            Icon(imageVector = if(isExpanded) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown, contentDescription = "Expand row icon",
                modifier = Modifier
                    .padding(16.dp)
                    .align(if(isExpanded) Alignment.Bottom else Alignment.CenterVertically).clickable { isExpanded = !isExpanded })

    }



    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        MealsCategoriesScreen({ })
    }
}