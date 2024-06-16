package com.saeongjima.signup

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saeongjima.domain.usecase.SignUpUseCase
import com.saeongjima.model.account.SignUpInformation
import com.saeongjima.signup.personalinformation.PersonalInformationUiState
import com.saeongjima.signup.signininformation.SignInInformationUiState
import com.saeongjima.signup.signupdone.SignUpDoneUiState
import com.saeongjima.signup.universityinformation.UniversityInformationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val personalInformationUiState: MutableStateFlow<PersonalInformationUiState> =
        MutableStateFlow(PersonalInformationUiState())

    private val signInInformationUiState: MutableStateFlow<SignInInformationUiState> =
        MutableStateFlow(SignInInformationUiState())

    private val universityInformationUiState: MutableStateFlow<UniversityInformationUiState> =
        MutableStateFlow(UniversityInformationUiState())

    private val _idCardImageUri: MutableStateFlow<String> = MutableStateFlow("")
    val idCardImageUri: StateFlow<String> = _idCardImageUri.asStateFlow()

    private val _signUpDoneUiState: MutableStateFlow<SignUpDoneUiState> = MutableStateFlow(
        SignUpDoneUiState()
    )
    val signUpDoneUiState: StateFlow<SignUpDoneUiState> = _signUpDoneUiState.asStateFlow()

    private val _universityCertificationUiState: MutableStateFlow<UniversityCertificationUiState> =
        MutableStateFlow(UniversityCertificationUiState())
    val universityCertificationUiState: StateFlow<UniversityCertificationUiState> =
        _universityCertificationUiState.asStateFlow()

    fun updateProfileImageUri(value: String) {
        _signUpDoneUiState.update {
            it.copy(profileImageUri = value)
        }
    }

    fun updateIdCardImageUri(value: String) {
        _idCardImageUri.value = value
    }

    fun updateUniversityCheckImageUri(value: String) {
        _universityCertificationUiState.update { it.copy(imageUri = value) }
    }

    fun updatePersonalInformation(uiState: PersonalInformationUiState) {
        personalInformationUiState.value = uiState
    }

    fun updateSignInInformation(uiState: SignInInformationUiState) {
        signInInformationUiState.value = uiState
        _signUpDoneUiState.update { it.copy(id = uiState.id.value) }
    }

    fun updateUniversityInformation(uiState: UniversityInformationUiState) {
        universityInformationUiState.value = uiState
        _signUpDoneUiState.update {
            it.copy(
                entryYear = uiState.userEntranceYear,
                department = uiState.userDepartment,
            )
        }
    }

    fun signUp(uriToFile: (Uri) -> File, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _universityCertificationUiState.update { it.copy(isLoading = true) }
            val authImgFile =
                async(Dispatchers.IO) { uriToFile(Uri.parse(universityCertificationUiState.value.imageUri)) }
            val residentImgFile =
                async(Dispatchers.IO) { uriToFile(Uri.parse(idCardImageUri.value)) }

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
                authImgFile = authImgFile.await(),
                residentImgFile = residentImgFile.await(),
            )
                .onSuccess {
                    onSuccess()
                }
                .onFailure {
                    // TODO: 오류 처리 어떻게 할지 합의 후 변경
                }
            _universityCertificationUiState.update { it.copy(isLoading = false) }
        }
    }
}
