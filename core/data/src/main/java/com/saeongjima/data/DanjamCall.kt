package com.saeongjima.data

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

class DanjamCall<T : Any>(private val call: Call<T>, private val responseType: Type) :
    Call<Result<T>> {

    override fun clone(): Call<Result<T>> = DanjamCall(call.clone(), responseType)

    override fun execute(): Response<Result<T>> {
        throw UnsupportedOperationException(NOT_SUPPORT_EXECUTE)
    }

    override fun enqueue(callback: Callback<Result<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val networkResponse = when {
                    responseType == Unit::class -> Result.success(Unit as T)
                    !response.isSuccessful ->
                        Result.failure(
                            ApiError(
                                response.code(),
                                response.errorBody()?.toString()
                            )
                        )

                    response.body() == null ->
                        Result.failure(UnexpectedError(NullPointerException(RESPONSE_BODY_IS_NULL)))

                    else -> Result.success(response.body()!!)
                }
                callback.onResponse(this@DanjamCall, Response.success(networkResponse))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResponse: Result<T> = when (t) {
                    is IOException ->
                        Result.failure(
                            NetworkError(t, FAILED_TO_CONNECT_TO_SERVER)
                        )

                    else -> Result.failure(UnexpectedError(t, UNEXPECTED_ERROR))
                }
                callback.onResponse(this@DanjamCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()

    companion object {
        private const val NOT_SUPPORT_EXECUTE = "execute를 지원하지 않습니다."
        private const val RESPONSE_BODY_IS_NULL = "응답이 비어있습니다."
        private const val FAILED_TO_CONNECT_TO_SERVER = "인터넷 연결을 확인해주세요. :)"
        private const val UNEXPECTED_ERROR = "에상치 못한 오류가 발생하였습니다."
    }
}
