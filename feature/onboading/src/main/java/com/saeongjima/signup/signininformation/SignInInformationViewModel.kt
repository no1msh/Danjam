package com.saeongjima.signup.signininformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saeongjima.domain.usecase.ValidateIdUseCase
import com.saeongjima.domain.usecase.ValidateNicknameUseCase
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
class SignInInformationViewModel @Inject constructor(
    private val validateIdUseCase: ValidateIdUseCase,
    private val validateNicknameUseCase: ValidateNicknameUseCase,
) : ViewModel() {

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
            _signInInformationUiState.update { it.copy(isLoading = true) }
            validateIdUseCase(_signInInformationUiState.value.id)
                .onSuccess { isValid ->
                    _signInInformationUiState.update {
                        it.copy(
                            id = if (isValid) it.id else Id(""),
                            isIdDuplication = if (isValid) DuplicateState.NotDuplicated else DuplicateState.Duplicated
                        )
                    }
                }
                .onFailure {
                    // TODO: 오류 처리 어떻게 할지 합의 후 변경
                }
            _signInInformationUiState.update { it.copy(isLoading = false) }
        }
    }

    fun checkNicknameDuplication() {
        viewModelScope.launch {
            _signInInformationUiState.update { it.copy(isLoading = true) }
            validateNicknameUseCase(_signInInformationUiState.value.nickname)
                .onSuccess { isValid ->
                    _signInInformationUiState.update {
                        it.copy(
                            nickname = if (isValid) it.nickname else Nickname(""),
                            isNicknameDuplication = if (isValid) DuplicateState.NotDuplicated else DuplicateState.Duplicated
                        )
                    }
                }
                .onFailure {
                    // TODO: 오류 처리 어떻게 할지 합의 후 변경
                }
            _signInInformationUiState.update { it.copy(isLoading = false) }
        }
    }
}
