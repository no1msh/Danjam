package com.saeongjima.signup.personalinformation

import com.saeongjima.model.DuplicateState

data class PersonalInformationUiState(
    val name: String = "",
    val isMale: Boolean = true,
    val birthDay: String = "",
    val email: String = "",
    val isValidEmail: DuplicateState = DuplicateState.NotChecked,
) {
    fun hasMetAllConditions(): Boolean {
        return name.isNotBlank() &&
                birthDay.isNotBlank() &&
                email.isNotBlank() &&
                isValidEmail == DuplicateState.NotDuplicated
    }
}
