package com.saeongjima.model.account

@JvmInline
value class Nickname(override val value: String) : SupportedLengthValidator {

    override fun isKeepRange(): Boolean {
        return value.length in MIN_LENGTH..MAX_LENGTH
    }

    fun isValid(): Boolean {
        return isKeepRange()
    }

    companion object {
        private const val MIN_LENGTH = 2
        private const val MAX_LENGTH = 10
    }
}
