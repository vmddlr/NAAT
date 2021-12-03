package com.example.naat.data.network.service

import com.example.naat.data.model.Response.UserResponse
import com.example.naat.data.network.api.LoginApi
import javax.inject.Inject


class LoginService @Inject constructor(
    private val loginApi: LoginApi
){

    suspend fun getUserService(userName: String, password: String) : UserResponse {
        val response = loginApi.getUserApi(
            username = userName,
            password = password
        )

        return if(response.isSuccessful) {
            response.body()!!
        } else {
            UserResponse(
                success = false,
                error = "invalid_request: Bad credentials"
            )
        }
    }
}
