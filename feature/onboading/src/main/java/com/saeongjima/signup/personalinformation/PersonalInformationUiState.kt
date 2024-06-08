package com.saeongjima.signup.personalinformation

data class PersonalInformationUiState(
    val name: String = "",
    val isMale: Boolean = true,
    val birthDay: String = "",
    val email: String = "",
    val isValidEmail: Boolean = false,
) {
    fun hasMetAllConditions(): Boolean {
        return name.isNotBlank() &&
                birthDay.isNotBlank() &&
                email.isNotBlank() &&
                isValidEmail
    }
}
