package com.example.wezelnews.onboarding

import androidx.annotation.DrawableRes
import com.example.wezelnews.R

data class Page(
    val title:String,
    val description:String,
    @DrawableRes val image:Int
)
val pages = listOf<Page>(
    Page(
        title = "Bitcoin And International Trading",
        description = "Do International Trading And Bitcoin With Wezel News",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Climate Changing And Working Of Weather",
        description = "Weather Climate And Traveling With Wezel News",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Food And Blogging",
        description = "Food Trades And Blogging With Work With Wezel News",
        image = R.drawable.onboarding3
    )
)