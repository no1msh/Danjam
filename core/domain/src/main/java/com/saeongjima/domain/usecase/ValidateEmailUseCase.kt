package com.saeongjima.domain.usecase

import com.saeongjima.data.repository.SignUpRepository
import com.saeongjima.model.account.Email
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(email: Email): Result<Boolean> {
        return signUpRepository.validateEmail(email)
    }
}
