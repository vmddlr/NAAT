package com.example.naat.data.network.api

import com.example.naat.data.model.Response.UserResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("token")
    suspend fun getUserApi(
        @Field("grant_type") grant_type: String = "password",
        @Field("username") username: String,
        @Field("password") password: String,
    ): Response<UserResponse>
}