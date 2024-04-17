package com.saeongjima.signin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel : ViewModel() {
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

}

sealed interface SignInUiState {
    data object Loading : SignInUiState
    data object Success : SignInUiState
    data object Fail : SignInUiState
    data object Idle : SignInUiState
}
