package com.saeongjima.data.repository

import java.io.File

interface UserRepository {
    suspend fun resisterProfileImage(profileImageFile: File): Result<Unit>
}
