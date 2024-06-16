package com.saeongjima.signup.universityinformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saeongjima.domain.usecase.GetDepartmentsUseCase
import com.saeongjima.domain.usecase.GetUniversitiesUseCase
import com.saeongjima.model.Department
import com.saeongjima.model.EntranceYears
import com.saeongjima.model.University
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityInformationViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase,
    private val getDepartmentsUseCase: GetDepartmentsUseCase,
) : ViewModel() {
    private val _universityInformationUiState: MutableStateFlow<UniversityInformationUiState> =
        MutableStateFlow(
            UniversityInformationUiState(
                isLoading = true,
                entranceYears = EntranceYears.years
            )
        )
    val universityInformationUiState: StateFlow<UniversityInformationUiState> =
        _universityInformationUiState.asStateFlow()

    private val _universities: MutableStateFlow<List<University>> = MutableStateFlow(emptyList())

    init {
        initUiState()
    }

    private fun initUiState() {
        viewModelScope.launch {
            getUniversitiesUseCase()
                .onSuccess { universities: List<University> ->
                    _universityInformationUiState.update { uiState ->
                        uiState.copy(
                            universities = universities.map { it.name }
                        )
                    }

                    _universities.value = universities
                }
                .onFailure {
                    // TODO: 오류 처리 어떻게 할지 합의 후 변경
                }
            _universityInformationUiState.update { it.copy(isLoading = false) }
        }
    }

    fun updateUserEntranceYear(index: Int) {
        _universityInformationUiState.update {
            it.copy(userEntranceYear = it.entranceYears[index])
        }
    }

    fun updateUserUniversity(index: Int) {
        _universityInformationUiState.update {
            it.copy(userUniversity = _universities.value[index])
        }
        viewModelScope.launch {
            _universityInformationUiState.update { it.copy(isLoading = true) }
            getDepartmentsUseCase(_universities.value[index].id)
                .onSuccess { departments: List<Department> ->
                    _universityInformationUiState.update { uiState ->
                        uiState.copy(
                            userDepartment = "",
                            departments = departments,
                        )
                    }
                }
                .onFailure {
                    // TODO: 오류 처리 어떻게 할지 합의 후 변경
                }
            _universityInformationUiState.update { it.copy(isLoading = false) }
        }
    }

    fun updateUserDepartment(value: String) {
        _universityInformationUiState.update {
            it.copy(userDepartment = value)
        }
    }
}
