package com.ranzan.tasty.model.remote

import com.ranzan.tasty.Constants.HEADER_HOST
import com.ranzan.tasty.Constants.HEADER_KEY
import retrofit2.http.GET
import retrofit2.http.Header


interface ApiClient {

    @GET("feeds/list")
    suspend fun getData(
        @Header("x-rapidapi-host") host: String = HEADER_HOST,
        @Header("x-rapidapi-key") key: String = HEADER_KEY
    ): ResponseModel

}