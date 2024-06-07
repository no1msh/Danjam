package com.saeongjima.model.account

sealed interface InputValidator {
    val value: String
}

interface NotSupportedLength : InputValidator {
    fun isKeepRange(): Boolean
}
