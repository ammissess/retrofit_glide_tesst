package com.example.retrofitcallapi

import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET("/productts")
    suspend fun getProducts(): Response<Albums>



}