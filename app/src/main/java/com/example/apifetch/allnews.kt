package com.example.apifetch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface AllnewsService {
    @GET("trendings")
    fun getTopNewsHeadlines(
        @Header("x-rapidapi-key") apiKey: String,
        @Header("x-rapidapi-host") apiHost: String
    ): Call<Allnews>
}
