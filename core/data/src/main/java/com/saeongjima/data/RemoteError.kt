package com.saeongjima.data

import java.io.IOException

sealed class RemoteError : Exception() {
    abstract fun toStringForUser(): String
    abstract fun toStringForDeveloper(): String
}

data class ApiError(val responseCode: Int, val description: String? = null) : RemoteError() {
    override fun toStringForUser(): String {
        return "오류코드: $responseCode 서버에 일시적인 문제가 있습니다."
    }

    override fun toStringForDeveloper(): String {
        return "responseCode: $responseCode - $description"
    }
}

data class NetworkError(val exception: IOException, val description: String? = null) :
    RemoteError() {
    override fun toStringForUser(): String {
        return "인터넷 연결을 확인해주세요."
    }

    override fun toStringForDeveloper(): String {
        return "$exception"
    }
}

data class UnexpectedError(val exception: Throwable, val description: String? = null) :
    RemoteError() {
    override fun toStringForUser(): String {
        return "예기치 못한 오류가 발생하였습니다. \n잠시후 다시 시도해주세요."
    }

    override fun toStringForDeveloper(): String {
        return "$exception $description"
    }
}

