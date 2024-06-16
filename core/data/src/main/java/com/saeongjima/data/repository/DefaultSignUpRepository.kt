package com.saeongjima.data.repository

import com.saeongjima.data.api.SignUpService
import com.saeongjima.data.mapper.toRequest
import com.saeongjima.model.account.Email
import com.saeongjima.model.account.Id
import com.saeongjima.model.account.Nickname
import com.saeongjima.model.account.SignUpInformation
import okhttp3.RequestBody
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

    override suspend fun signUp(signUpInformation: SignUpInformation): Result<Unit> {
        val signUpRequestPartMap = HashMap<String, RequestBody>()
        val signUpRequest = signUpInformation.toRequest()

        signUpRequestPartMap[USERNAME_KEY] = signUpRequest.username
        signUpRequestPartMap[PASSWORD_KEY] = signUpRequest.password
        signUpRequestPartMap[SCHOOL_ID_KEY] = signUpRequest.schoolId
        signUpRequestPartMap[GENDER_KEY] = signUpRequest.gender
        signUpRequestPartMap[NICKNAME_KEY] = signUpRequest.nickname
        signUpRequestPartMap[EMAIL_KEY] = signUpRequest.email
        signUpRequestPartMap[BIRTH_KEY] = signUpRequest.birth
        signUpRequestPartMap[ENTRY_YEAR_KEY] = signUpRequest.entryYear
        signUpRequestPartMap[MAJOR_KEY] = signUpRequest.major

        return signUpService.signUp(
            signUpRequest = signUpRequestPartMap,
            authImgFile = signUpRequest.authImgFile,
            residentImgFile = signUpRequest.residentImgFile,
        )
    }

    companion object {
        private const val USERNAME_KEY = "username"
        private const val PASSWORD_KEY = "password"
        private const val SCHOOL_ID_KEY = "schoolId"
        private const val GENDER_KEY = "gender"
        private const val NICKNAME_KEY = "nickname"
        private const val EMAIL_KEY = "email"
        private const val BIRTH_KEY = "birth"
        private const val ENTRY_YEAR_KEY = "entryYear"
        private const val MAJOR_KEY = "major"
    }
}
