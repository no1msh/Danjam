package com.saeongjima.data.di

import com.saeongjima.data.repository.DefaultSignInRepository
import com.saeongjima.data.repository.SignInRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindsSignInRepository(
        repository: DefaultSignInRepository,
    ): SignInRepository
}
