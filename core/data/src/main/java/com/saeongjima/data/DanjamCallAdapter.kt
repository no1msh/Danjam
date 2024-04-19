package com.saeongjima.data

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class DanjamCallAdapter<R : Any> private constructor(private val responseType: Type) :
    CallAdapter<R, Call<Result<R>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<Result<R>> = DanjamCall(call, responseType)

    companion object CallAdapterFactory : CallAdapter.Factory() {
        private const val RETURN_TYPE_IS_NOT_PARAMETERIZED_TYPE =
            "Return type must be parameterized as Call<Result<Foo>> or Call<Result<out Foo>>"
        private const val RESPONSE_MUST_BE_PARAMETERIZED = "" +
            "Response must be parameterized as Result<Foo> or Result<out Foo>"

        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            if (getRawType(returnType) != Call::class.java) {
                return null
            }

            check(returnType is ParameterizedType) {
                RETURN_TYPE_IS_NOT_PARAMETERIZED_TYPE
            }

            val responseType: Type = getParameterUpperBound(0, returnType)

            if (getRawType(responseType) != Result::class.java) {
                return null
            }

            check(responseType is ParameterizedType) {
                RESPONSE_MUST_BE_PARAMETERIZED
            }

            val successBodyType = getParameterUpperBound(0, responseType)

            return DanjamCallAdapter<Any>(successBodyType)
        }
    }
}
