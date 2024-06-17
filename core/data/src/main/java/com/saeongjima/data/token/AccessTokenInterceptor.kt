package com.saeongjima.data.token

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val jwtManager: JwtManager,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String? = runBlocking {
            jwtManager.getAccessJwt()
        }

        return chain.proceed(
            chain
                .request()
                .newBuilder()
                .addHeader(HEADER_ACCESS_NAME, "$token")
                .build()
        )
    }

    companion object {
        const val HEADER_ACCESS_NAME = "access"
    }
}
