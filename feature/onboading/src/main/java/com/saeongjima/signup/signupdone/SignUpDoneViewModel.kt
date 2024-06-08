package com.saeongjima.signup.signupdone

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpDoneViewModel @Inject constructor() : ViewModel() {
    private val _profileImageUri: MutableStateFlow<String> = MutableStateFlow("")
    val profileImageUri: StateFlow<String> = _profileImageUri.asStateFlow()

    fun updateProfileImageUri(value: String) {
        _profileImageUri.value = value
    }
}
