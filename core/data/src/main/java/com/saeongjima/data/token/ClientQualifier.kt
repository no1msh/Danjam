package com.saeongjima.data.token

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class PublicClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class PublicRetrofit


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SignInClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SignInRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NeedAuthClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NeedAuthRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RefreshTokenClient
