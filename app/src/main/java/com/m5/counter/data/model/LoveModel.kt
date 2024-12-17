package com.m5.counter.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoveModel(
    @SerializedName("fname")
    val firstName: String,
    @SerializedName("sname")
    val secondName: String,
    val percentage: String,
    val result: String
) : Serializable
