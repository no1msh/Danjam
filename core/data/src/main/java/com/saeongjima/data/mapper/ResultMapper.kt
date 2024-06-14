package com.saeongjima.data.mapper

private const val UNEXPECTED_ERROR_IN_MAPPER = "Unexpected Error in Mapper"

fun <T, R> Result<T>.toResult(toModel: (T) -> R): Result<R> {
    this.onSuccess {
        return Result.success(toModel(it))
    }.onFailure {
        return Result.failure(it)
    }

    return Result.failure(Exception(UNEXPECTED_ERROR_IN_MAPPER))
}
