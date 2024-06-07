package com.saeongjima.model.account

@JvmInline
value class Password(override val value: String) :
    SupportedLengthValidator,
    EnglishContainValidator,
    NumberContainValidator {

    override fun isKeepRange(): Boolean {
        return value.length in MIN_LENGTH..MAX_LENGTH
    }

    companion object {
        private const val MIN_LENGTH = 8
        private const val MAX_LENGTH = 20
    }
}
