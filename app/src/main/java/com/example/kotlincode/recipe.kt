package com.example.kotlincode

data class recipe(
    val limit: Int,
    val recipes: List<RecipeX>,
    val skip: Int,
    val total: Int
)