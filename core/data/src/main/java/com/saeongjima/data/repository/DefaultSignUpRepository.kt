package com.saeongjima.data.repository

import com.saeongjima.data.api.SignUpService
import com.saeongjima.model.account.Email
import com.saeongjima.model.account.Id
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
}
