package com.saeongjima.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface SignUpService {
    @GET("/api/user/check-email")
    suspend fun validateEmail(
        @Query("email")
        email: String
    ): Result<Boolean>
}