package com.saeongjima.signup.universityinformation

import com.saeongjima.model.Department
import com.saeongjima.model.University

data class UniversityInformationUiState(
    val isLoading: Boolean = false,
    val entranceYears: List<String> = emptyList(),
    val universities: List<String> = emptyList(),
    val departments: List<Department> = emptyList(),
    val userEntranceYear: String = "",
    val userUniversity: University = University(0,""),
    val userDepartment: String = "",
) {
    fun hasMetAllConditions(): Boolean {
        return userEntranceYear.isNotBlank() &&
                userUniversity.name.isNotBlank() &&
                userDepartment.isNotBlank()
    }
}
