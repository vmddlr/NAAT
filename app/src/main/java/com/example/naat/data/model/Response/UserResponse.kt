package com.example.naat.data.model.Response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponse(
    @SerializedName("access_token") var access_token: String = "",
    @SerializedName("token_type") var token_type: String = "",
    @SerializedName("refresh_token") var refresh_token: String = "",
    @SerializedName("expires_in") var expires_in: Int = 0,
    @SerializedName("scope") var scope: String = "",
    @SerializedName("jti") var jti: String = "",
    @SerializedName("success") var success: Boolean = true,
    @SerializedName("error") var error: String = "",
) : Serializable
