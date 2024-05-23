package com.saeongjima.data.repository

import com.saeongjima.data.api.SignInService
import com.saeongjima.data.api.model.SignInRequest
import javax.inject.Inject

class DefaultSignInRepository @Inject constructor(
    private val signInService: SignInService
) : SignInRepository {
    override suspend fun signIn(username: String, password: String): Result<Unit> {
        return signInService.postSignIn(SignInRequest(username, password))
    }
}
