package com.example.gads_leaderboard.model

import com.google.gson.annotations.SerializedName

data class Learner(
    val name: String,
    @SerializedName(value = "number", alternate = ["hours", "score"])
    val number: Int,
    val country: String,
    val badgeUrl: String
)