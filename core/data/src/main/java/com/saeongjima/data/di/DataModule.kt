package com.saeongjima.data.di

import com.saeongjima.data.repository.DefaultSignInRepository
import com.saeongjima.data.repository.DefaultSignUpRepository
import com.saeongjima.data.repository.DefaultUniversityRepository
import com.saeongjima.data.repository.SignInRepository
import com.saeongjima.data.repository.SignUpRepository
import com.saeongjima.data.repository.UniversityRepository
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

    @Binds
    abstract fun bindsSignUpRepository(
        repository: DefaultSignUpRepository,
    ): SignUpRepository

    @Binds
    abstract fun bindsUniversityRepository(
        repository: DefaultUniversityRepository
    ): UniversityRepository
}
