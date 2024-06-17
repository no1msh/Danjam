package com.saeongjima.data.token

import com.saeongjima.data.api.RefreshTokenService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

/**
 * Authenticator는 401 Not Authorized 에러가 발생했을 때, 작동할 로직을 담을 수 있습니다.
 * - Refresh token을 통해 access token과 refresh token을 갱신합니다.
 * */

class DefaultAuthenticator @Inject constructor(
    private val jwtManager: JwtManager,
    private val refreshTokenService: RefreshTokenService,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            var token: String? = null

            val refreshTokenResponse: Response = runBlocking { refreshTokenService.refreshToken() }

            if (refreshTokenResponse.isSuccessful) {
                runBlocking {
                    withContext(Dispatchers.IO) {
                        val accessToken = response.headers[ACCESS_KEY]
                        val refreshToken = response.headers[SET_COOKIE_KEY]

                        if (!accessToken.isNullOrBlank() && !refreshToken.isNullOrBlank()) {
                            jwtManager.saveAccessJwt(accessToken)
                            jwtManager.saveRefreshJwt(refreshToken)

                            token = accessToken
                        }
                    }
                }
            }

            return if (token != null) {
                response.request.newBuilder()
                    .header(HEADER_ACCESS, "$token")
                    .build()
            } else null
        }
    }

    companion object {
        private const val HEADER_ACCESS = "access"
        private const val ACCESS_KEY = "access"
        private const val SET_COOKIE_KEY = "Set-Cookie"
    }
}
