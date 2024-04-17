package com.saeongjima.signup.departmentselect

import com.saeongjima.model.Department

data class DepartmentItemUiState(
    val index: Int,
    val department: Department,
    val isSelected: Boolean = false,
)
