package com.saeongjima.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface SignUpService {
    @GET("/api/user/check-email")
    suspend fun validateEmail(
        @Query("email")
        email: String
    ): Result<Boolean>

    @GET("/api/user/check-username")
    suspend fun validateId(
        @Query("username")
        username: String
    ): Result<Boolean>

    @GET("/api/user/check-nickname")
    suspend fun validateNickname(
        @Query("nickname")
        nickname: String
    ): Result<Boolean>
}
