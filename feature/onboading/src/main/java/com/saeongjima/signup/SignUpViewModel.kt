package com.saeongjima.signup

import androidx.lifecycle.ViewModel
import com.saeongjima.signup.personalinformation.PersonalInformationUiState
import com.saeongjima.signup.signininformation.SignInInformationUiState
import com.saeongjima.signup.universityinformation.UniversityInformationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
        MutableStateFlow(UniversityInformationUiState())
    val universityInformationUiState: StateFlow<UniversityInformationUiState> =
        _universityInformationUiState.asStateFlow()

    private val _idCardImageUri: MutableStateFlow<String> = MutableStateFlow("")
    val idCardImageUri: StateFlow<String> = _idCardImageUri.asStateFlow()

    private val _universityCheckImageUri: MutableStateFlow<String> = MutableStateFlow("")
    val universityCheckImageUri: StateFlow<String> = _universityCheckImageUri.asStateFlow()

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
}
