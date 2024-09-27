package com.example.news.data.remote

import com.example.news.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page")page:Int,
        @Query("sources")sources:String,
        @Query("apikey")apiKey:String
    ):NewsResponse
}