package com.saeongjima.signup.personalinformation

import androidx.lifecycle.ViewModel
import com.saeongjima.model.DuplicateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PersonalInformationViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<PersonalInformationUiState> =
        MutableStateFlow(PersonalInformationUiState())
    val uiState: StateFlow<PersonalInformationUiState> = _uiState.asStateFlow()

    fun updateName(value: String) {
        _uiState.update {
            it.copy(name = value)
        }
    }

    fun updateGender(value: Boolean) {
        _uiState.update {
            it.copy(isMale = value)
        }
    }

    fun updateBirthDay(value: String) {
        _uiState.update {
            it.copy(birthDay = value)
        }
    }

    fun updateEmail(value: String) {
        _uiState.update {
            it.copy(
                email = value,
                isValidEmail = DuplicateState.NotChecked
            )
        }
    }

    // TODO: 서버 연결시 로직 변경
    fun checkValidationEmail() {
        _uiState.update {
            it.copy(
                isValidEmail = DuplicateState.NotDuplicated
            )
        }
    }
}
