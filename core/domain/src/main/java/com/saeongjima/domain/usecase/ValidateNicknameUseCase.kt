package com.saeongjima.domain.usecase

import com.saeongjima.data.repository.SignUpRepository
import com.saeongjima.model.account.Nickname
import javax.inject.Inject

class ValidateNicknameUseCase @Inject constructor(
    private val signupRepository: SignUpRepository,
) {
    suspend operator fun invoke(nickname: Nickname): Result<Boolean> {
        return signupRepository.validateNickname(nickname)
    }
}
