package com.saeongjima.data.token

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * 사용자가 로그인할 때 사용되는 인터셉터입니다.
 *
 * 로그인 서버요청의 응답 결과에 따라 성공적이라면 access / refresh token을 저장합니다.
 * */
class SignInInterceptor @Inject constructor(
    private val jwtManager: JwtManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val response: Response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            return response
        }

        runBlocking {
            val accessToken = response.headers[ACCESS_KEY]
            val refreshToken = response.headers[SET_COOKIE_KEY]

            if (!accessToken.isNullOrBlank() && !refreshToken.isNullOrBlank()) {
                launch {
                    jwtManager.clearAllTokens()
                    launch { jwtManager.saveAccessJwt(accessToken) }
                    launch { jwtManager.saveRefreshJwt(refreshToken) }
                }
            }
        }

        return response
    }

    companion object {
        const val ACCESS_KEY = "access"
        const val SET_COOKIE_KEY = "Set-Cookie"
    }
}
