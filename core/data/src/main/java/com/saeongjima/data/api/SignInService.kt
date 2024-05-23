package com.saeongjima.data.api

import com.saeongjima.data.api.model.SignInRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("/login")
    suspend fun postSignIn(
        @Body
        signInRequest: SignInRequest
    ): Result<Unit>
}
