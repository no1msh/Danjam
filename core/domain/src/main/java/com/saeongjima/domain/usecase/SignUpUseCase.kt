package com.saeongjima.domain.usecase

import com.saeongjima.data.repository.SignUpRepository
import com.saeongjima.model.account.SignUpInformation
import java.io.File
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(
        signUpInformation: SignUpInformation,
        authImgFile: File,
        residentImgFile: File
    ): Result<Unit> {
        return signUpRepository.signUp(signUpInformation, authImgFile, residentImgFile)
    }
}
