package com.saeongjima.data.di

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
        retrofit: Retrofit,
    ): SignInService = retrofit.create(SignInService::class.java)
}
