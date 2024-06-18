package com.saeongjima.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.saeongjima.data.BuildConfig
import com.saeongjima.data.api.RefreshTokenService
import com.saeongjima.data.api.SignInService
import com.saeongjima.data.api.SignUpService
import com.saeongjima.data.api.UniversityService
import com.saeongjima.data.api.UserService
import com.saeongjima.data.token.NeedAuthRetrofit
import com.saeongjima.data.token.PublicRetrofit
import com.saeongjima.data.token.RefreshTokenClient
import com.saeongjima.data.token.SignInRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServiceModule {
    private const val CONTENT_TYPE = "application/json"

    @Singleton
    @Provides
    fun providesSignInService(
        @SignInRetrofit retrofit: Retrofit,
    ): SignInService = retrofit.create(SignInService::class.java)

    @Singleton
    @Provides
    fun providesSignUpService(
        @PublicRetrofit retrofit: Retrofit,
    ): SignUpService = retrofit.create(SignUpService::class.java)

    @Singleton
    @Provides
    fun providesUniversityService(
        @PublicRetrofit retrofit: Retrofit,
    ): UniversityService = retrofit.create(UniversityService::class.java)

    @Singleton
    @Provides
    fun providesRefreshTokenService(
        @RefreshTokenClient okHttpClient: OkHttpClient,
    ): RefreshTokenService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .client(okHttpClient)
        .build()
        .create(RefreshTokenService::class.java)

    @Singleton
    @Provides
    fun providesUserService(
        @NeedAuthRetrofit retrofit: Retrofit,
    ): UserService = retrofit.create(UserService::class.java)
}
