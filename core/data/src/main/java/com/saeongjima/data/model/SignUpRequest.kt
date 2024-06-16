package com.saeongjima.data.model

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class SignUpRequest(
    val username: RequestBody,
    val password: RequestBody,
    val schoolId: RequestBody,
    val gender: RequestBody,
    val nickname: RequestBody,
    val email: RequestBody,
    val birth: RequestBody,
    val entryYear: RequestBody,
    val major: RequestBody,
    val authImgFile: MultipartBody.Part,
    val residentImgFile: MultipartBody.Part,
)
