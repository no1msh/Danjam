package com.saeongjima.data.mapper

import com.saeongjima.data.model.SignUpRequest
import com.saeongjima.model.account.SignUpInformation
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

private const val IMAGE_MEDIA_TYPE = "image/jpg"
private const val TEXT_MEDIA_TYPE = "text/plain"
private const val MALE = 1
private const val FEMALE = 0
private const val AUTH_IMG_NAME = "authImgFile"
private const val RESIDENT_IMG_NAME = "residentImgFile"

fun SignUpInformation.toRequest(): SignUpRequest {
    val authRequestFile = authImgFile.asRequestBody(IMAGE_MEDIA_TYPE.toMediaTypeOrNull())
    val authMultiPartBody = MultipartBody.Part.createFormData(
        name = AUTH_IMG_NAME,
        filename = authImgFile.name,
        body = authRequestFile
    )

    val residentRequestFile = residentImgFile.asRequestBody(IMAGE_MEDIA_TYPE.toMediaTypeOrNull())
    val residentMultiPartBody = MultipartBody.Part.createFormData(
        name = RESIDENT_IMG_NAME,
        filename = residentImgFile.name,
        body = residentRequestFile
    )

    return SignUpRequest(
        username = id.value.toRequestBody(TEXT_MEDIA_TYPE.toMediaTypeOrNull()),
        password = password.value.toRequestBody(TEXT_MEDIA_TYPE.toMediaTypeOrNull()),
        schoolId = university.id.toString().toRequestBody(TEXT_MEDIA_TYPE.toMediaTypeOrNull()),
        gender = (if (gender) MALE else FEMALE).toString()
            .toRequestBody(TEXT_MEDIA_TYPE.toMediaTypeOrNull()),
        nickname = nickname.value.toRequestBody(TEXT_MEDIA_TYPE.toMediaTypeOrNull()),
        email = email.value.toRequestBody(TEXT_MEDIA_TYPE.toMediaTypeOrNull()),
        birth = birth.toRequestBody(TEXT_MEDIA_TYPE.toMediaTypeOrNull()),
        entryYear = entryYear.toRequestBody(TEXT_MEDIA_TYPE.toMediaTypeOrNull()),
        major = major.toRequestBody(TEXT_MEDIA_TYPE.toMediaTypeOrNull()),
        authImgFile = authMultiPartBody,
        residentImgFile = residentMultiPartBody,
    )
}
