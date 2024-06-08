package com.saeongjima.signup.universityinformation

data class UniversityInformationUiState(
    val entranceYears: List<String> = emptyList(),
    val universities: List<String> = emptyList(),
    val departments: List<String> = emptyList(),
    val userEntranceYear: String = "",
    val userUniversity: String = "",
    val userDepartment: String = "",
) {
    fun hasMetAllConditions(): Boolean {
        return userEntranceYear.isNotBlank() &&
                userUniversity.isNotBlank() &&
                userDepartment.isNotBlank()
    }
}
