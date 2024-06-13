package com.saeongjima.signup.personalinformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saeongjima.domain.usecase.ValidateEmailUseCase
import com.saeongjima.model.DuplicateState
import com.saeongjima.model.account.Email
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalInformationViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase
) : ViewModel() {

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
                email = Email(value),
                isValidEmail = DuplicateState.NotChecked
            )
        }
    }

    fun checkValidationEmail() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            validateEmailUseCase(email = uiState.value.email)
                .onSuccess { isValid ->
                    _uiState.update {
                        it.copy(
                            email = if (isValid) it.email else Email(""),
                            isValidEmail = if (isValid) DuplicateState.NotDuplicated else DuplicateState.Duplicated
                        )
                    }
                }
                .onFailure {
                    // TODO: 오류 처리 어떻게 할지 합의 후 변경
                }
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}
