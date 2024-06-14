package com.saeongjima.data.repository

import com.saeongjima.data.api.SignUpService
import com.saeongjima.model.account.Email
import com.saeongjima.model.account.Id
import com.saeongjima.model.account.Nickname
import com.saeongjima.model.account.SignUpInformation
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class DefaultSignUpRepository @Inject constructor(
    private val signUpService: SignUpService
) : SignUpRepository {
    override suspend fun validateEmail(email: Email): Result<Boolean> {
        return signUpService.validateEmail(email.value)
    }

    override suspend fun validateId(id: Id): Result<Boolean> {
        return signUpService.validateId(id.value)
    }

    override suspend fun validateNickname(nickname: Nickname): Result<Boolean> {
        return signUpService.validateNickname(nickname.value)
    }

    override suspend fun signUp(
        signUpInformation: SignUpInformation,
        authImgFile: File,
        residentImgFile: File
    ): Result<Unit> {
        val map = HashMap<String, RequestBody>()

        val authRequestFile = authImgFile.asRequestBody("image/jpg".toMediaTypeOrNull())
        val authMultiPartBody =
            MultipartBody.Part.createFormData("authImgFile", authImgFile.name, authRequestFile)

        val residentRequestFile = residentImgFile.asRequestBody("image/jpg".toMediaTypeOrNull())
        val residentMultiPartBody = MultipartBody.Part.createFormData(
            "residentImgFile",
            residentImgFile.name,
            residentRequestFile
        )

        val id = signUpInformation.id.value.toRequestBody("text/plain".toMediaTypeOrNull())
        val password =
            signUpInformation.password.value.toRequestBody("text/plain".toMediaTypeOrNull())
        val gender =
            signUpInformation.gender.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val nickname =
            signUpInformation.nickname.value.toRequestBody("text/plain".toMediaTypeOrNull())
        val email = signUpInformation.email.value.toRequestBody("text/plain".toMediaTypeOrNull())
        val birth = signUpInformation.birth.toRequestBody("text/plain".toMediaTypeOrNull())
        val university = signUpInformation.university.id.toString()
            .toRequestBody("text/plain".toMediaTypeOrNull())
        val entryYear =
            signUpInformation.entryYear.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val major = signUpInformation.major.toRequestBody("text/plain".toMediaTypeOrNull())

        map["username"] = id
        map["password"] = password
        map["schoolId"] = university
        map["gender"] = gender
        map["nickname"] = nickname
        map["email"] = email
        map["birth"] = birth
        map["entryYear"] = entryYear
        map["major"] = major

        return signUpService.signUp(
            signUpRequest = map,
            authImgFile = authMultiPartBody,
            residentImgFile = residentMultiPartBody,
        )
    }
}
