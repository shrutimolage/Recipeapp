package com.example.kotlincode

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

        @GET("recipe")
        fun getProductData() : Call<recipe>
    }

//    @FormUrlEncoded
//    @POST("dashboard-api/dashboard")
//        fun dashboard(@Field("Desig_id")Desig_id: Int):Response<dashboard>
//
//
//        @GET("year-api/year")
//        fun getYears():Call<year>


