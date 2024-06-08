package com.saeongjima.signup

import androidx.lifecycle.ViewModel
import com.saeongjima.signup.personalinformation.PersonalInformationUiState
import com.saeongjima.signup.signininformation.SignInInformationUiState
import com.saeongjima.signup.universityinformation.UniversityInformationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _personalInformationUiState: MutableStateFlow<PersonalInformationUiState> =
        MutableStateFlow(PersonalInformationUiState())
    val personalInformationUiState: StateFlow<PersonalInformationUiState> =
        _personalInformationUiState.asStateFlow()

    private val _signInInformationUiState: MutableStateFlow<SignInInformationUiState> =
        MutableStateFlow(SignInInformationUiState())
    val signInInformationUiState: StateFlow<SignInInformationUiState> =
        _signInInformationUiState.asStateFlow()

    private val _universityInformationUiState: MutableStateFlow<UniversityInformationUiState> =
        MutableStateFlow(
            UniversityInformationUiState(
                entranceYears = List(MAX_ENTRANCE_YEARS) { "${LocalDateTime.now().year - it}" },
                universities = listOf("고려대학교 세종캠퍼스", "홍익대학교 세종캠퍼스"),  /*TODO: 서비스 대학 추가시 변경*/
                departments = listOf("게임소프트웨어전공", "게임그래픽디자인전공") // TODO: 서버 연결 시 변경
            )
        )
    val universityInformationUiState: StateFlow<UniversityInformationUiState> =
        _universityInformationUiState.asStateFlow()

    private val _idCardImageUri: MutableStateFlow<String> = MutableStateFlow("")
    val idCardImageUri: StateFlow<String> = _idCardImageUri.asStateFlow()

    private val _universityCheckImageUri: MutableStateFlow<String> = MutableStateFlow("")
    val universityCheckImageUri: StateFlow<String> = _universityCheckImageUri.asStateFlow()


    fun updateUserEntranceYear(value: String) {
        _universityInformationUiState.update {
            it.copy(userEntranceYear = value)
        }
    }

    fun updateUserUniversity(value: String) {
        _universityInformationUiState.update {
            it.copy(userUniversity = value)
        }
    }

    fun updateUserDepartment(value: String) {
        _universityInformationUiState.update {
            it.copy(userDepartment = value)
        }
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

    fun updateSignInInformation(signInInformationUiState: SignInInformationUiState) {
        _signInInformationUiState.value = signInInformationUiState
    }

    companion object {
        private const val MAX_ENTRANCE_YEARS = 10
    }
}
