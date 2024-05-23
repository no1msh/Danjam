package com.saeongjima.domain.usecase

import com.saeongjima.data.repository.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        return signInRepository.signIn(username, password)
    }
}
