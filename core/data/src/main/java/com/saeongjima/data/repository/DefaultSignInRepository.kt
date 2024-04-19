package com.saeongjima.data.repository

import com.saeongjima.data.di.SignInRequest
import com.saeongjima.data.di.SignInService
import javax.inject.Inject

class DefaultSignInRepository @Inject constructor(
    private val signInService: SignInService
) : SignInRepository {
    override suspend fun signIn(username: String, password: String): Result<Unit> {
        return signInService.postSignIn(SignInRequest(username, password))
    }
}
