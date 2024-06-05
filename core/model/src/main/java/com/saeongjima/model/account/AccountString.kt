package com.saeongjima.model.account

@JvmInline
value class AccountString(val value: String) {

    fun getValidateState(): AccountValidation = when {
        Regex("^[a-zA-Z0-9]{$MIN_ID_LENGTH,$MAX_ID_LENGTH}\$").matches(value) -> AccountValidation.Success

        value.isEmpty() -> AccountValidation.Yet

        value.length !in MIN_ID_LENGTH..MAX_ID_LENGTH -> AccountValidation.FailedByUnsupportedLength

        !Regex("^[a-zA-Z0-9]+\$").matches(value) -> AccountValidation.FailedByUnsupportedCharacter

        else -> AccountValidation.FailedByUnexpectedCondition
    }

    companion object {
        private const val MIN_ID_LENGTH = 8
        private const val MAX_ID_LENGTH = 20
    }
}
