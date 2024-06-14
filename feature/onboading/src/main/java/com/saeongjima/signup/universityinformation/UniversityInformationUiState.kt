package com.saeongjima.signup.universityinformation

import com.saeongjima.model.Department

data class UniversityInformationUiState(
    val isLoading: Boolean = false,
    val entranceYears: List<String> = emptyList(),
    val universities: List<String> = emptyList(),
    val departments: List<Department> = emptyList(),
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
