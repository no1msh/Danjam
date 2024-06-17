package com.saeongjima.data.token

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RefreshTokenInterceptor @Inject constructor(
    private val tokenManager: JwtManager,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String? = runBlocking {
            tokenManager.getRefreshJwt()
        }

        return chain.proceed(
            chain
                .request()
                .newBuilder()
                .addHeader(COOKIE_REFRESH_NAME, "$token")
                .build()
        )
    }

    companion object {
        const val COOKIE_REFRESH_NAME = "refresh"
    }
}
