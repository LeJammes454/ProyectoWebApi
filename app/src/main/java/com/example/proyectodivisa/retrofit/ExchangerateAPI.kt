package com.example.proyectodivisa.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ExchangerateAPI {
    //@get:GET("v6/064e537b97fd03303fa8e8ae/latest/USD")
    //val posts : Call<Posts>

    @GET
    fun getPosts(@Url url: String): Call<Posts>


}