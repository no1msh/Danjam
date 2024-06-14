package com.saeongjima.data.repository

import com.saeongjima.model.account.Email
import com.saeongjima.model.account.Id
import com.saeongjima.model.account.Nickname
import com.saeongjima.model.account.SignUpInformation
import java.io.File

interface SignUpRepository {
    suspend fun validateEmail(email: Email): Result<Boolean>
    suspend fun validateId(id: Id): Result<Boolean>
    suspend fun validateNickname(nickname: Nickname): Result<Boolean>
    suspend fun signUp(
        signUpInformation: SignUpInformation,
        authImgFile: File,
        residentImgFile: File,
    ): Result<Unit>
}
