package com.saeongjima.data.di

import com.saeongjima.data.api.SignInService
import com.saeongjima.data.api.SignUpService
import com.saeongjima.data.api.UniversityService
import com.saeongjima.data.token.PublicRetrofit
import com.saeongjima.data.token.SignInRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServiceModule {
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
}
