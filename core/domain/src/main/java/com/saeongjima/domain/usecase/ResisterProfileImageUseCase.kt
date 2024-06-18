package com.saeongjima.domain.usecase

import com.saeongjima.data.repository.UserRepository
import java.io.File
import javax.inject.Inject

class ResisterProfileImageUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(profileImage: File): Result<Unit> {
        return userRepository.resisterProfileImage(profileImage)
    }
}
