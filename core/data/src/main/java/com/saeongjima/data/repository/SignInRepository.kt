package com.saeongjima.data.repository

interface SignInRepository {
    suspend fun signIn(username: String, password: String): Result<Unit>
}
