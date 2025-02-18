package com.saeongjima.signin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saeongjima.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel() {
    var id: MutableState<String> = mutableStateOf("")
        private set

    var password: MutableState<String> = mutableStateOf("")
        private set

    private val _signInUiState: MutableStateFlow<SignInUiState> =
        MutableStateFlow(SignInUiState.Idle)
    val signInUiState: StateFlow<SignInUiState> = _signInUiState.asStateFlow()

    fun updateId(text: String) {
        id.value = text
    }

    fun updatePassword(text: String) {
        password.value = text
    }

    fun signIn() {
        viewModelScope.launch {
            _signInUiState.value = SignInUiState.Loading
            signInUseCase(username = id.value, password = password.value)
                .onSuccess {
                    _signInUiState.value = SignInUiState.Success
                }
                .onFailure {
                    _signInUiState.value = SignInUiState.Fail
                }
        }
    }
}

sealed interface SignInUiState {
    data object Loading : SignInUiState
    data object Success : SignInUiState
    data object Fail : SignInUiState
    data object Idle : SignInUiState
}
