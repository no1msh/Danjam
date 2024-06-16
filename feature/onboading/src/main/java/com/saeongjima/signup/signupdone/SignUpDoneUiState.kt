package com.saeongjima.signup.signupdone

data class SignUpDoneUiState(
    val isLoading: Boolean = false,
    val id: String = "",
    val department: String = "",
    val entryYear: String = "",
    val profileImageUri: String = ""
)
