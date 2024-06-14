package com.saeongjima.data.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
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

    @Multipart
    @POST("/api/user")
    suspend fun signUp(
        @PartMap signUpRequest: HashMap<String, RequestBody>,
        @Part authImgFile: MultipartBody.Part,
        @Part residentImgFile: MultipartBody.Part
    ): Result<Unit>
}
