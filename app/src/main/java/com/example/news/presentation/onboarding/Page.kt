package com.example.news.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.news.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
        image = R.drawable.onboarding3
    ),
)
