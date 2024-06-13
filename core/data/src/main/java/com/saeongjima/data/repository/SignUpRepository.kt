package com.saeongjima.data.repository

import com.saeongjima.model.account.Email
import com.saeongjima.model.account.Id

interface SignUpRepository {
    suspend fun validateEmail(email: Email): Result<Boolean>
    suspend fun validateId(id: Id): Result<Boolean>
}
