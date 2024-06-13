package com.saeongjima.data.repository

import com.saeongjima.model.account.Email

interface SignUpRepository {
    suspend fun validateEmail(email: Email): Result<Boolean>
}
