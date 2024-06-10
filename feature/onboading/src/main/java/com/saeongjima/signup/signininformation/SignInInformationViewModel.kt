package com.saeongjima.signup.signininformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saeongjima.model.DuplicateState
import com.saeongjima.model.account.Id
import com.saeongjima.model.account.Nickname
import com.saeongjima.model.account.Password
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInInformationViewModel @Inject constructor() : ViewModel() {

    private val _signInInformationUiState: MutableStateFlow<SignInInformationUiState> =
        MutableStateFlow(SignInInformationUiState())
    val signInInformationUiState: StateFlow<SignInInformationUiState> =
        _signInInformationUiState.asStateFlow()

    fun updateId(value: String) {
        _signInInformationUiState.update {
            it.copy(
                id = Id(value),
                isIdDuplication = DuplicateState.NotChecked,
            )
        }
    }

    fun updateNickname(value: String) {
        _signInInformationUiState.update {
            it.copy(
                nickname = Nickname(value),
                isNicknameDuplication = DuplicateState.NotChecked,
            )
        }
    }

    fun updatePassword(value: String) {
        _signInInformationUiState.update {
            it.copy(password = Password(value))
        }
    }

    fun updatePasswordRepeat(value: String) {
        _signInInformationUiState.update {
            it.copy(passwordRepeat = Password(value))
        }
    }

    fun checkIdDuplication() {
        viewModelScope.launch {
            _signInInformationUiState.update {
                it.copy(
                    id = Id(""),
                    isIdDuplication = DuplicateState.NotDuplicated
                ) // TODO: 서버 통신 연결 시 변경)
            }
        }
    }

    fun checkNicknameDuplication() {
        viewModelScope.launch {
            _signInInformationUiState.update {
                it.copy(isNicknameDuplication = DuplicateState.NotDuplicated) // TODO: 서버 통신 연결 시 변경)
            }
        }
    }
}
