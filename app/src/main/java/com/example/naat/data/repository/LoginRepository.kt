package com.example.naat.data.repository

import com.example.naat.data.network.service.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginService: LoginService
) {

    suspend fun getUserRepository(username: String, password: String) =
        loginService.getUserService(username, password)
}