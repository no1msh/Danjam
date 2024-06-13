package com.saeongjima.domain.usecase

import com.saeongjima.data.repository.SignUpRepository
import com.saeongjima.model.account.Id
import javax.inject.Inject

class ValidateIdUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(id: Id): Result<Boolean> {
        return signUpRepository.validateId(id)
    }
}
