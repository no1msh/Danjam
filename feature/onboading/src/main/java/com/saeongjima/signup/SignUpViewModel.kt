package com.saeongjima.signup

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saeongjima.domain.usecase.SignUpUseCase
import com.saeongjima.model.account.SignUpInformation
import com.saeongjima.signup.personalinformation.PersonalInformationUiState
import com.saeongjima.signup.signininformation.SignInInformationUiState
import com.saeongjima.signup.universityinformation.UniversityInformationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _personalInformationUiState: MutableStateFlow<PersonalInformationUiState> =
        MutableStateFlow(PersonalInformationUiState())
    val personalInformationUiState: StateFlow<PersonalInformationUiState> =
        _personalInformationUiState.asStateFlow()

    private val _signInInformationUiState: MutableStateFlow<SignInInformationUiState> =
        MutableStateFlow(SignInInformationUiState())
    val signInInformationUiState: StateFlow<SignInInformationUiState> =
        _signInInformationUiState.asStateFlow()

    private val _universityInformationUiState: MutableStateFlow<UniversityInformationUiState> =
        MutableStateFlow(UniversityInformationUiState())
    val universityInformationUiState: StateFlow<UniversityInformationUiState> =
        _universityInformationUiState.asStateFlow()

    private val _idCardImageUri: MutableStateFlow<String> = MutableStateFlow("")
    val idCardImageUri: StateFlow<String> = _idCardImageUri.asStateFlow()

    private val _universityCheckImageUri: MutableStateFlow<String> = MutableStateFlow("")
    val universityCheckImageUri: StateFlow<String> = _universityCheckImageUri.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

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

    fun updateUniversityInformation(universityInformationUiState: UniversityInformationUiState) {
        _universityInformationUiState.value = universityInformationUiState
    }

    fun signUp(uriToFile: (Uri) -> File) {
        viewModelScope.launch {
            _isLoading.value = true
            signUpUseCase(
                signUpInformation = SignUpInformation(
                    id = signInInformationUiState.value.id,
                    password = signInInformationUiState.value.password,
                    gender = if (personalInformationUiState.value.isMale) 1 else 0,
                    nickname = signInInformationUiState.value.nickname,
                    email = personalInformationUiState.value.email,
                    birth = personalInformationUiState.value.birthDay,
                    university = universityInformationUiState.value.userUniversity,
                    entryYear = universityInformationUiState.value.userEntranceYear.toInt(),
                    major = universityInformationUiState.value.userDepartment,
                ),
                authImgFile = uriToFile(Uri.parse(universityCheckImageUri.value)),
                residentImgFile = uriToFile(Uri.parse(idCardImageUri.value)),
            )
                .onSuccess {
                    Log.d("bandal", "signUp: 회원가입 성공")
                }
                .onFailure {
                    Log.d("bandal", "signUp: 회원가입 실패")
                }
            _isLoading.value = false
        }
    }
}
