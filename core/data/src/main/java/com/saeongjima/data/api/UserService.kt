package com.saeongjima.data.api

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UserService {
    @Multipart
    @POST("/api/user/myprofile/profileImg")
    suspend fun resisterProfileImage(
        @Part profileImage: MultipartBody.Part,
    ): Result<Unit>
}
