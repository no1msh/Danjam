package com.saeongjima.signup.signininformation

import com.saeongjima.model.account.Id
import com.saeongjima.model.account.Nickname
import com.saeongjima.model.account.Password
import com.saeongjima.signup.DuplicateState

data class SignInInformationUiState(
    val id: Id = Id(""),
    val isIdDuplication: DuplicateState = DuplicateState.NotChecked,
    val nickname: Nickname = Nickname(""),
    val isNicknameDuplication: DuplicateState = DuplicateState.NotChecked,
    val password: Password = Password(""),
    val passwordRepeat: Password = Password(""),
) {
    fun hasMetAllConditions(): Boolean {
        return id.isValid() &&
                isIdDuplication == DuplicateState.NotDuplicated &&
                nickname.isValid() &&
                isNicknameDuplication == DuplicateState.NotDuplicated &&
                password.isValid() &&
                password == passwordRepeat
    }
}

