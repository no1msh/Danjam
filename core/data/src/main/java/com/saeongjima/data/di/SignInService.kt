package com.saeongjima.data.di

import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("/login")
    suspend fun postSignIn(
        @Body
        signInRequest: SignInRequest
    ): Result<Unit>
}
