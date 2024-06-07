package com.saeongjima.model.account

sealed interface InputValidator {
    val value: String
}

interface SupportedLengthValidator : InputValidator {
    fun isKeepRange(): Boolean
}

interface EnglishContainValidator : InputValidator {
    fun hasAlphabet(): Boolean {
        val regex = Regex("[a-zA-Z]")
        return regex.containsMatchIn(value)
    }
}
