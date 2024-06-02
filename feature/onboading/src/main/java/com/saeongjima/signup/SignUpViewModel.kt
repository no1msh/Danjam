package com.saeongjima.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _personalInformationUiState: MutableStateFlow<PersonalInformationUiState> =
        MutableStateFlow(PersonalInformationUiState())

    val personalInformationUiState: StateFlow<PersonalInformationUiState> =
        _personalInformationUiState.asStateFlow()

    private val _idCardImageUri: MutableStateFlow<String> = MutableStateFlow("")
    val idCardImageUri: StateFlow<String> = _idCardImageUri.asStateFlow()

    private val _universityCheckImageUri: MutableStateFlow<String> = MutableStateFlow("")
    val universityCheckImageUri: StateFlow<String> = _universityCheckImageUri.asStateFlow()


    private val _entranceYear: MutableStateFlow<List<String>> = MutableStateFlow(
        List(MAX_ENTRANCE_YEARS) { "${LocalDateTime.now().year - it}" }
    )
    val entranceYear: StateFlow<List<String>> = _entranceYear.asStateFlow()
    private val _universities: MutableStateFlow<List<String>> = MutableStateFlow(
        listOf("고려대학교 세종캠퍼스", "홍익대학교 세종캠퍼스") // TODO: 서비스 대학 추가시 변경
    )
    val universities: StateFlow<List<String>> = _universities.asStateFlow()
    private val _departments: MutableStateFlow<List<String>> = MutableStateFlow(
        listOf("게임소프트웨어전공", "게임그래픽디자인전공") // TODO: 서버 연결 시 변경
    )
    val departments: StateFlow<List<String>> = _departments.asStateFlow()

    private val _userEntranceYear: MutableStateFlow<String> = MutableStateFlow("")
    val userEntranceYear: StateFlow<String> = _userEntranceYear.asStateFlow()
    private val _userUniversity: MutableStateFlow<String> = MutableStateFlow("")
    val userUniversity: StateFlow<String> = _userUniversity.asStateFlow()
    private val _userDepartment: MutableStateFlow<String> = MutableStateFlow("")
    val userDepartment: StateFlow<String> = _userDepartment.asStateFlow()

    fun updateUserEntranceYear(value: String) {
        _userEntranceYear.value = value
    }

    fun updateUserUniversity(value: String) {
        _userUniversity.value = value
    }

    fun updateUserDepartment(value: String) {
        _userDepartment.value = value
    }

    fun updateIdCardImageUri(value: String) {
        _idCardImageUri.value = value
    }

    fun updateUniversityCheckImageUri(value: String) {
        _universityCheckImageUri.value = value
    }

    fun updatePersonalInformation(personalInformationUiState: PersonalInformationUiState) {
        _personalInformationUiState.value = personalInformationUiState
    }

    fun fetchDepartments(university: String) {
        _departments.value = listOf(
            "게임소프트웨어전공",
            "게임그래픽디자인전공",
        )
    }


    companion object {
        private const val MAX_ENTRANCE_YEARS = 10
    }
}
