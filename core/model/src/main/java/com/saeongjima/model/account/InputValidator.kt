package com.saeongjima.model.account

sealed interface InputValidator {
    val value: String
}

interface SupportedLengthValidator : InputValidator {
    fun isKeepRange(): Boolean
}
