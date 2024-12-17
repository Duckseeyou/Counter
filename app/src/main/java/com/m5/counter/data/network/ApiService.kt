package com.m5.counter.data.network

import com.m5.counter.data.model.LoveModel
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("getPercentage")
    fun getPercentage(
        @Query("fname") firstName: String,
        @Query("sname") secondName: String,
        @Header("X-RapidAPI-Key") apiKey: String,
        @Header("X-RapidAPI-Host") apiHost: String
    ): Call<LoveModel>

}