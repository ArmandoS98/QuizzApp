package com.tekun.quizzapp.data.model

import com.google.gson.annotations.SerializedName

data class QuizModel(
    @SerializedName("question") val question: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("options") val options: String,
    @SerializedName("answer") val answer: Int
)
