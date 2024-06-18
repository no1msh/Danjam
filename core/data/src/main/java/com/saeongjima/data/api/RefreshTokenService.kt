package com.saeongjima.data.api

import okhttp3.Response
import retrofit2.http.POST

interface RefreshTokenService {
    @POST("/api/token")
    suspend fun refreshToken(): Response
}
