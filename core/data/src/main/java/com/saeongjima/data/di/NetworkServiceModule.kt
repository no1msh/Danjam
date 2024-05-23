package com.saeongjima.data.di

import com.saeongjima.data.api.SignInService
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
}
