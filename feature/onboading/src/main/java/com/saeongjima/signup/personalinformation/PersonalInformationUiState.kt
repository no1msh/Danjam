package com.saeongjima.signup.personalinformation

import com.saeongjima.model.DuplicateState
import com.saeongjima.model.account.Email

data class PersonalInformationUiState(
    val isLoading: Boolean = false,
    val name: String = "",
    val isMale: Boolean = true,
    val birthDay: String = "",
    val email: Email = Email(""),
    val isValidEmail: DuplicateState = DuplicateState.NotChecked,
) {
    fun hasMetAllConditions(): Boolean {
        return name.isNotBlank() &&
                birthDay.isNotBlank() &&
                email.value.isNotBlank() &&
                isValidEmail == DuplicateState.NotDuplicated
    }
}
