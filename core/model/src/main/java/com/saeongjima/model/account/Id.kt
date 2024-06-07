package com.saeongjima.model.account

@JvmInline
value class Id(override val value: String) : NotSupportedLength {

    override fun isKeepRange(): Boolean {
        return value.length in MIN_LENGTH..MAX_LENGTH
    }

    companion object {
        private const val MIN_LENGTH = 2
        private const val MAX_LENGTH = 10
    }
}
