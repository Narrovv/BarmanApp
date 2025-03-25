package com.example.myapplication.data

data class Drinki(
    val id: Int,
    val name: String,
    val recipe: String,
    val imageId: Int,
    val steps: List<String>
)