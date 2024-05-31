package com.saeongjima.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PersonalInformationViewModel @Inject constructor() : ViewModel() {
    var name by mutableStateOf("")
        private set

    private val _isMale: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isMale: StateFlow<Boolean> = _isMale.asStateFlow()

    var birthDay by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    private val _isValidEmail: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isValidEmail: StateFlow<Boolean> = _isValidEmail.asStateFlow()

    private var validatedEmail: String = ""

    val isAllConditionsMet = combine(
        snapshotFlow { name },
        snapshotFlow { birthDay },
        isValidEmail,
    ) { name, birthDay, isValidEmail ->
        name.isNotBlank() && birthDay.isNotBlank() && isValidEmail
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    private val _uiState: MutableStateFlow<PersonalInformationUiState> =
        MutableStateFlow(PersonalInformationUiState())
    val uiState: StateFlow<PersonalInformationUiState> = _uiState.asStateFlow()

    fun updateName(value: String) {
        name = value
    }

    fun updateGender(value: Boolean) {
        _isMale.value = value
    }

    fun updateBirthDay(value: String) {
        birthDay = value
    }

    fun updateEmail(value: String) {
        email = value
        _isValidEmail.value = validatedEmail == value
    }

    // TODO: 서버 연결시 로직 변경
    fun checkValidationEmail(): Boolean {
        _isValidEmail.value = true
        validatedEmail = email
        return true
    }
}


data class PersonalInformationUiState(
    val name: String = "",
    val isMale: Boolean = true,
    val birthDay: String = "",
    val email: String = "",
    val isValidEmail: Boolean = false,
)

