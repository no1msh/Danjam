package com.saeongjima.data.repository

import com.saeongjima.data.api.UserService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val userService: UserService,
) : UserRepository {
    override suspend fun resisterProfileImage(profileImageFile: File): Result<Unit> {
        val profileImageRequestBody =
            profileImageFile.asRequestBody(IMAGE_MEDIA_TYPE.toMediaTypeOrNull())

        val profileImageMultiPartBody = MultipartBody.Part.createFormData(
            name = PROFILE_IMG_NAME,
            filename = profileImageFile.name,
            body = profileImageRequestBody
        )

        return userService.resisterProfileImage(profileImageMultiPartBody)
    }

    companion object {
        private const val IMAGE_MEDIA_TYPE = "image/jpg"
        private const val PROFILE_IMG_NAME = "file"
    }
}
